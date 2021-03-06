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

final class PosLong private (val value: Long) extends AnyVal with BoundedLong {
  override def toString: String = s"PosLong($value)"
}

object PosLong {
  def from(value: Long): Option[PosLong] =
    if (value > 0L) Some(new PosLong(value)) else None
  import language.experimental.macros
  implicit def apply(value: Long): PosLong = macro PosLongMacro.apply
}

