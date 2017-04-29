/* Monoids
A Monoid is an abstraction of the concept of adding or combining.

Formally, a monoid for a type A is:
• an operation combine with type (A,A)=>A
• an element empty of type A

Eg:

trait Monoid[A] {
  def combine(x: A, y: A): A
  def empty: A
}

In addition to providing these operations, monoids must formally
obey several laws.
For all values x, y, and z, in A, combine must be associative and
empty must be an identity element:

Integer subtraction, for example, is not a monoid because subtraction
is not associative

A semigroup is simply the combine part of a monoid without an empty element
*/
import cats.Monoid

implicit val booleanAndMonoid : Monoid[Boolean] = new Monoid[Boolean]{
  override def empty: Boolean = true

  override def combine(x: Boolean, y: Boolean): Boolean = x && y
}

//implicit val booleanOrMonoid : Monoid[Boolean] = new Monoid[Boolean]{
//  override def empty: Boolean = false
//
//  override def combine(x: Boolean, y: Boolean): Boolean = x || y
//}
//booleanOrMonoid.combine(false, false)

booleanAndMonoid.combine(false, true)


import cats.std.int._
import cats.syntax.semigroup._

1 |+| 10

true |+| false
false |+| false



