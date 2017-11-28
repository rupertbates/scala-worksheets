
// Either[String, Option[A]]
import cats.data.{OptionT, WriterT}
import cats.instances.either._
import cats.syntax.applicative._

type ErrorOr[A] = Either[String, A]
type ErrorOrOption[A] = OptionT[ErrorOr, A]

val a = 10.pure[ErrorOrOption]

def addOne(num: Int): ErrorOrOption[Int] =
  (num + 1).pure[ErrorOrOption]

val e = addOne(5)
val f = for {
  a <- addOne(5)
  b <- addOne(a)
} yield b

val r = f.value.right.get

def addOneWithEither(num: Int): Either[String, Int] = Right(num + 1)
val x = addOneWithEither(5)
for {
  a <- addOneWithEither(5)
  b <- addOneWithEither(a)
  c <- addOneWithEither(b)
} yield b


// Writer[String, Option[A]]
import cats.data.{OptionT, Writer}
import cats.syntax.writer._
import cats.instances.string._

type Logged[A] = Writer[String, A]
type LoggedOption[A] = OptionT[Logged, A]

def addOneWithWriter(num: Int): Writer[String, Int] = {
  num.writer(s"Added one to $num")
}

val c = addOneWithWriter(5)

val b = for {
  a <- addOneWithWriter(5)
  b <- addOneWithWriter(a)
  c <- addOneWithWriter(b)
} yield c

b

// Future[Writer[String, Int]]
import scala.concurrent.Future
import cats.instances.future._
import scala.concurrent.ExecutionContext.Implicits.global
type FutureWriter[Int] = WriterT[Future, String, Int]

//def futureAddOneWithWriter(num: Int): FutureWriter[Int] =  Future.successful(addOneWithWriter(num))

val s = for {
  i <- 5.pure[FutureWriter]
  j <- 6.pure[FutureWriter]
} yield {
  System.out.println(j)
  j + 1
}

