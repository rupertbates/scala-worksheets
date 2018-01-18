import cats.data._
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Awaitable, Future}

def await[T](a: Awaitable[T]) = Await.result(a, 1000.seconds)

case class Error(msg: String)

val et: EitherT[Future, Error, List[Int]] = EitherT.right(Future.successful(List(1, 2, 3)))

def addOne(l: List[Int]): EitherT[Future, Error, List[String]] =
  EitherT.right(Future.successful(l.map(_ + 1).map(_.toString)))

val stack = for {
  a <- et
  b <- addOne(a)
} yield b

await(stack.value)
