/*
 * Copyright 2001-2014 Artima, Inc.
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
package org.scalactic.numbers

import org.scalatest._
import scala.collection.mutable.WrappedArray
import OptionValues._
import org.scalactic.StrictCheckedEquality._

class PosIntSpec extends Spec with Matchers {
  object `A PosInt` {
    object `should offer a from factory method that` {
      def `returns Some[PosInt] if the passed Int is greater than 0`
      {
        PosInt.from(50).value.value shouldBe 50
        PosInt.from(100).value.value shouldBe 100
      }
      def `returns None if the passed Int is NOT greater than 0` {
        PosInt.from(0) shouldBe None
        PosInt.from(-1) shouldBe None
        PosInt.from(-99) shouldBe None
      }
    } 
    def `should have a pretty toString` {
      PosInt.from(42).value.toString shouldBe "PosInt(42)"
    }
    def `should be automatically widened to compatible AnyVal targets` {
      (PosInt.from(3).get: Int) shouldEqual 3
      (PosInt.from(3).get: Long) shouldEqual 3L
      (PosInt.from(3).get: Float) shouldEqual 3.0F
      (PosInt.from(3).get: Double) shouldEqual 3.0
      (PosInt.from(3).get: PozInt) shouldEqual PozInt.from(3).get
      (PosInt.from(3).get: PozLong) shouldEqual PozLong.from(3L).get
      (PosInt.from(3).get: PozFloat) shouldEqual PozFloat.from(3.0F).get
      (PosInt.from(3).get: PozDouble) shouldEqual PozDouble.from(3.0).get
    }
    object `when a compatible AnyVal is passed to a + method invoked on it` {
      def `should give the same AnyVal type back at compile time, and correct value at runtime` {
        // When adding a "primitive"
        val opInt = PosInt(3) + 3
        opInt shouldEqual 6

        val opLong = PosInt(3) + 3L
        opLong shouldEqual 6L

        val opFloat = PosInt(3) + 3.0F
        opFloat shouldEqual 6.0F

        val opDouble = PosInt(3) + 3.0
        opDouble shouldEqual 6.0

        // When adding a Pos*
        val opPosInt = PosInt(3) + PosInt(3)
        opPosInt shouldEqual 6

        val opPosLong = PosInt(3) + PosLong(3L)
        opPosLong shouldEqual 6L

        val opPosFloat = PosInt(3) + PosFloat.from(3.0F).get
        opPosFloat shouldEqual 6.0F

        val opPosDouble = PosInt(3) + PosDouble.from(3.0).get
        opPosDouble shouldEqual 6.0

        // When adding a *Poz
        val opPoz = PosInt(3) + PozInt.from(3).get
        opPoz shouldEqual PozInt.from(6).get.value

        val opPozLong = PosInt(3) + PozLong.from(3L).get
        opPozLong shouldEqual PozLong.from(6L).get.value

        val opPozFloat = PosInt(3) + PozFloat.from(3.0F).get
        opPozFloat shouldEqual PozFloat.from(6.0F).get.value

        val opPozDouble = PosInt(3) + PozDouble.from(3.0).get
        opPozDouble shouldEqual PozDouble.from(6.0).get.value
      }
    }

    object `when created with apply method` {

      def `should compile when 8 is passed in`: Unit = {
        "PosInt(8)" should compile
        PosInt(8).value shouldEqual 8
      }

      def `should not compile when 0 is passed in`: Unit = {
        "PosInt(0)" shouldNot compile
      }

      def `should not compile when -8 is passed in`: Unit = {
        "PosInt(-8)" shouldNot compile
      }

      def `should not compile when x is passed in`: Unit = {
        val x: Int = -8
        "PosInt(x)" shouldNot compile
      }
    }
    object `when specified as a plain-old Int` {

      def takesPosInt(pos: PosInt): Int = pos.value

      def `should compile when 8 is passed in`: Unit = {
        "takesPosInt(8)" should compile
        takesPosInt(8) shouldEqual 8
      }

      def `should not compile when 0 is passed in`: Unit = {
        "takesPosInt(0)" shouldNot compile
      }

      def `should not compile when -8 is passed in`: Unit = {
        "takesPosInt(-8)" shouldNot compile
      }

      def `should not compile when x is passed in`: Unit = {
        val x: Int = -8
        "takesPosInt(x)" shouldNot compile
      }
    }
  }
}

