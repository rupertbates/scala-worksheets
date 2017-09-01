import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.data.Nested
import cats.implicits._

val l = List("a", "b", "c")

for {
  a <- l
  b <- 11 to 12
  c <- 13 to 14
} yield (a, b, c)


def sum(f: Int => Int, a: Int, b: Int) = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }

  loop(a, 0)
}

sum((a: Int) => a, 1, 4)


val fl = Future.successful(l)
def listOfList(s: String) = Future.successful(List(s, s, s))
val nfl = Nested(fl)

val r = nfl.map(listOfList(_))
//val res = Await.result(nfl.map(listOfList(_)), 10 seconds)

val res = fl.flatMap{
  list => list.flatMap(listOfList(_))
}









