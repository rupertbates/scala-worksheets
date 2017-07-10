import cats.data.Nested
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

val list1 = List(1, 2, 3)


val result = for {
  l <- list1

} yield {
  l
}


val l: Option[List[Int]] = Some(List(1, 2, 3, 4, 5))
val f = Future.successful(List(1,2,3))
val n = Nested(f)
val nl = Nested(l)

n.map(_ + 1)
nl.map(_ + 1)