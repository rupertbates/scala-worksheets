import shapeless.Generic

sealed trait Shape
final case class Rectangle(width: Double, height: Double) extends Shape
final case class Circle(radius: Double) extends Shape
val shapeGen = Generic[Shape]


shapeGen.to(Rectangle(5, 7))
shapeGen.to(Circle(22))
