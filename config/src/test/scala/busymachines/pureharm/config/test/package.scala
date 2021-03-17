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

package busymachines.pureharm.config

import busymachines.pureharm.anomaly._
import busymachines.pureharm.sprout._
import busymachines.pureharm.effects._

import scala.concurrent.duration._

/** @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 16 Jun 2019
  */
package object test {
  object PhantomInt            extends SproutSub[Int]
  object PhantomString         extends SproutSub[String]
  object PhantomBoolean        extends SproutSub[Boolean]
  object PhantomList           extends SproutSub[List[Int]]
  object PhantomSet            extends SproutSub[Set[String]]
  object PhantomFiniteDuration extends SproutSub[FiniteDuration]
  object PhantomDuration       extends SproutSub[Duration]

  type PhantomInt            = PhantomInt.Type
  type PhantomString         = PhantomString.Type
  type PhantomBoolean        = PhantomBoolean.Type
  type PhantomList           = PhantomList.Type
  type PhantomSet            = PhantomSet.Type
  type PhantomFiniteDuration = PhantomFiniteDuration.Type
  type PhantomDuration       = PhantomDuration.Type
  type SafePhantomInt        = SafePhantomInt.Type

  object SafePhantomInt extends SproutRefinedSubThrow[Int] {

    override def refine[F[_]: MonadThrow](value: Int): F[Int] = {
      import busymachines.pureharm.effects.implicits._
      if (value > 0) value.pure[F]
      else InvalidInputAnomaly(s"TEST_CASE_INVALID_SAFE_PHANTOM_ANOMALY").raiseError[F, Int]
    }
  }
}
