import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

//Chapter 1
//
// Show
val showInt = Show.apply[Int]
val showString = Show.apply[String]

showInt.show(123)
showString.show("hello")
123.show


//Eq
import cats.Eq

val eqInt = Eq[Int]

eqInt.eqv(4, 5)

import cats.syntax.eq._

5 === 5
5 =!= 5

import cats.instances.option._
import cats.syntax.option._
//Eq is invariant Some != Option - cast to Option
(Some(5) : Option[Int]) === (Some(5) : Option[Int])

//or import cats.syntax.option._ to use this formulation:
1.some === None
1.some =!= None
1.some === Some(2)

//Comparing custom types
import java.util.Date

import cats.instances.long._

implicit val dateEqual = Eq.instance[Date] { (date1, date2) =>
  date1.getTime === date2.getTime
}

dateEqual.eqv(new Date(), new Date())





