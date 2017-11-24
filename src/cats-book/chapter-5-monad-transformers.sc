import cats.syntax.applicative._
import cats.data.{EitherT, OptionT, Writer, WriterT}
import cats.instances.either._
import cats.instances.list._

// 1. List[Option]

type ListOption[A] = OptionT[List, A]

val result1: ListOption[Int] = OptionT(List(Option(10))) // result1: ListOption[Int] = OptionT(List(Some(10)))
val result2: ListOption[Int] = 32.pure[ListOption] // result2: ListOption[Int] = OptionT(List(Some(32)))

result1.flatMap { (x: Int) =>
  result2.map { (y: Int) =>
    x+y }
}

// 2. Either[String, Option[A]]

// Alias Either to a type constructor with one parameter:
type ErrorOr[A] = Either[String, A]
// Build our final monad stack using OptionT:
type ErrorOrOption[A] = OptionT[ErrorOr, A]

val a = 10.pure[ErrorOrOption]
val b = 32.pure[ErrorOrOption]


// 3. Future[Either[String, Option[A]]]

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.future._ // for Monad

type FutureEither[A] = EitherT[Future, String, A]
type FutureEitherOption[A] = OptionT[FutureEither, A]

val futureEitherOr: FutureEitherOption[Int] =
  for {
    c <- 10.pure[FutureEitherOption]
    d <- 32.pure[FutureEitherOption]
  } yield c + d


// 4. Future[Writer[String, Option[A]]]
////import cats.instances.int._
////import cats._
////import cats.data._
//import cats.implicits._
type Logged[A] = Writer[String, A]
type OptionWriter[A] = OptionT[Logged, A]
import cats.syntax.writer._
import cats.syntax.applicative._
import cats.instances.vector._
val e = 10.pure[OptionWriter]