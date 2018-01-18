import cats.data._
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Awaitable, Future}

def await[T](a: Awaitable[T]) = Await.result(a, 1000 seconds)
case class Error(msg: String)

val et: EitherT[Future, Error, List[Int]] = EitherT.right(Future.successful(List(1, 2, 3)))
val et2: EitherT[Future, Error, List[String]] = EitherT.right(Future.successful(List("one", "two", "three")))

et
val stack = for {
  a <- et
  b <- et2
} yield a.zip(b)

await(stack.value)
