//Product (AND)

import shapeless.{::, HNil}

val product: String :: Int :: Boolean :: HNil =
  "Sunday" :: 1 :: false :: HNil

product.head

import shapeless.Generic

case class IceCream(flavour: String, numberOfScoops: Int, inCone: Boolean)

val iceCreamGen = Generic[IceCream]

val iceCream = IceCream("chocolate", 2, true)

val repr = iceCreamGen.to(iceCream)

iceCreamGen.from(repr)

//Coproduct (OR)
import shapeless.{:+:, CNil, Inl, Inr}
case class Red()
case class Amber()
case class Green()
type Light = Red :+: Amber :+: Green :+: CNil

val red : Light = Inl(Red())
val green: Light = Inr(Inr(Inl(Green())))
val amber: Light = Inr(Inl(Amber()))

import shapeless.Generic._
sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape
val shapeGen = Generic[Shape]


