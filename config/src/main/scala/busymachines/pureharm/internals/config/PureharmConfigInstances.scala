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

import cats.Show
import cats.syntax.all._

import busymachines.pureharm.sprout._
import pureconfig.error.CannotConvert
import pureconfig.{ConfigReader, ConfigWriter}

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 16 Jun 2019
  */
object PureharmConfigInstances {

  trait Implicits extends PhantomTypeInstances

  trait PhantomTypeInstances {

    implicit final def pureharmNewTypeConfigRead[Underlying, New](implicit
      newType: NewType[Underlying, New],
      reader:  ConfigReader[Underlying],
    ): ConfigReader[New] = reader.map(newType.newType)

    implicit final def pureharmNewTypeConfigWriter[Underlying, New](implicit
      oldType: OldType[Underlying, New],
      reader:  ConfigWriter[Underlying],
    ): ConfigWriter[New] = reader.contramap(oldType.oldType)

    implicit final def pureharmRefinedTypeThrowConfigReader[Underlying, New, E](implicit
      refined: RefinedTypeThrow[Underlying, New],
      ushow:   Show[Underlying],
      reader:  ConfigReader[Underlying],
    ): ConfigReader[New] =
      reader.emap(s =>
        refined
          .newType[Either[Throwable, *]](s)
          .leftMap(e =>
            CannotConvert(
              value   = s.show,
              toType  = s"SproutRefined[${refined.symbolicName}]",
              because = e.toString,
            )
          )
      )
  }

}
