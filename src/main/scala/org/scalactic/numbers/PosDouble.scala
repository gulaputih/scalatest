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

import scala.language.implicitConversions

//
// Numbers greater than zero.
//

final class PosDouble private (val value: Double) extends AnyVal with BoundedDouble {
  override def toString: String = s"PosDouble($value)"
}

object PosDouble {
  def from(value: Double): Option[PosDouble] =
    if (value > 0.0) Some(new PosDouble(value)) else None
  import language.experimental.macros
  import scala.language.implicitConversions
  implicit def apply(value: Double): PosDouble = macro PosDoubleMacro.apply
}

