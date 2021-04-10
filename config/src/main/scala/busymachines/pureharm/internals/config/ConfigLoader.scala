/*
 * Copyright 2019 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package busymachines.pureharm.internals.config

import cats.syntax.all._
import cats.effect._
import pureconfig._
import pureconfig.error.ConfigReaderFailures

/** Important to note:
  * Given a case class:
  * {{{
  *   final case class TestConfig(
  *     port: Int,
  *     host: String,
  *     apiRoot: String,
  *   )
  * }}}
  * the ``apiRoot`` field will be read as ``api-root`` from the file.
  * So camelCase gets converted into "-" case.
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 20 Jun 2018
  */
trait ConfigLoader[Config] {

  /** This exists to force semi-auto-derivation, and it allows us to build
    * proper functions. Simply do:
    *
    * {{{
    *   import busymachines.pureharm.config._
    *   import busymachines.pureharm.config.implicits._
    *
    *   final case class ConfigX(
    *   //stuff
    *   )
    *
    *   object ConfigX extends ConfigLoader[ConfigX]{
    *     override val configReader: ConfigReader[ConfigX] = semiauto.derive[ConfigX]
    *
    *     //defined in supertype, and uses pureconfig.ConfigSource.default, override when necessary
    *     override def configSource[F[_]](implicit F: Sync[F]): F[ConfigSource] = ???
    *   }
    * }}}
    *
    * @return
    */
  implicit def configReader: ConfigReader[Config]

  /** Override this to provide non default source, simply by using pureconfig's useful data:
    *
    * //etc. or fetch your config from external service, etc.
    * F.delay(ConfigSource.file(...))
    */
  def configSource[F[_]](implicit F: Sync[F]): F[ConfigSource] = F.delay(ConfigSource.default)

  /** Override in subtypes when needed
    */
  def fromNamespace[F[_]: Sync](namespace: String): F[Config] = this.load(namespace)

  final def fromNamespaceR[F[_]: Sync](namespace: String): Resource[F, Config] =
    Resource.eval(fromNamespace(namespace))

  protected def load[F[_]: Sync](implicit reader: ConfigReader[Config]): F[Config] =
    for {
      source <- configSource[F].adaptError { case e => ConfigSourceLoadingAnomaly(e) }
      value  <- configToF(source.load[Config](Derivation.Successful(reader)))(Option.empty)
    } yield value

  protected def load[F[_]: Sync](namespace: String)(implicit reader: ConfigReader[Config]): F[Config] =
    for {
      source <- configSource[F].adaptError { case e => ConfigSourceLoadingAnomaly(e) }
      value  <- configToF(source.at(namespace).load[Config](Derivation.Successful(reader)))(Option(namespace))
        .adaptError { case f: ConfigAggregateAnomalies => f.withNamespace(namespace) }
    } yield value

  private def configToF[F[_]](
    thunk:     => Either[ConfigReaderFailures, Config]
  )(namespace: Option[String])(implicit F: Sync[F]): F[Config] =
    F.delay(thunk.leftMap((err: ConfigReaderFailures) => ConfigAggregateAnomalies(err, namespace): Throwable)).rethrow
}
