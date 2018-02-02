import cats.data._
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Awaitable, Future}

def await[T](a: Awaitable[T]) = Await.result(a, 1000.seconds)
def wrap[A](a: A): EitherT[Future, Error, A] =
  EitherT.right(Future.successful(a))

case class Error(msg: String)

val et: EitherT[Future, Error, List[Int]] = wrap(List(1, 2, 3))

def addOne(l: List[Int]): EitherT[Future, Error, List[Int]] =
  wrap(l.map(_ + 1))

def sum(l: List[Int]): EitherT[Future, Error, Int] =
  wrap(l.sum)

def log[A](a: A) = println("boo")

val stack = for {
  a <- et
  b <- addOne(a)
  c <- {
    log(b)
    sum(b)
  }
} yield c

await(stack.value)
