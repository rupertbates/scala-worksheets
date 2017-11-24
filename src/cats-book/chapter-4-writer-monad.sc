import cats.data.{OptionT, Writer}
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._


type Logged[A] = Writer[Vector[String], A]

// A Writer[W, A] carries two values: a log of type W and a result of type A.
// We can create a Writer from values of each type as follows:
val writer = Writer(Vector(
  "It was the best of times",
  "it was the worst of times"
), 1859)

// For convenience, Cats provides a way of creating Writers specifying only
// the log or the result. If we only have a result we can use the standard
// pure syntax. To do this we must have a Monoid[W] in scope so Cats knows
// how to produce an empty log:

val writer2 = 123.pure[Logged]

//If we have a log and no result we can create a Writer[Unit] using the
// tell syntax from cats.syntax.writer:
Vector("msg1", "msg2", "msg3").tell


//If we have both a result and a log, we can either use Writer.apply
// or we can use the writer syntax from cats.syntax.writer:
val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
val b = 123.writer(Vector("msg1", "msg2", "msg3"))

// We can extract the result and log from a Writer using the value
// and written methods respectively:
a.value
a.written.toList(2)

// We can extract both values at the same  me using the run method:
val (log, result) = b.run


def returnSomething(num: Int) = {
  (num + 1).writer(Vector("Added 1"))
}

returnSomething(3).written.toList.head


//import cats.data.WriterT
//import cats.implicits
//
//type FutureInt = Future[Int]
//type FutureWriterT[A] = WriterT[Future, String, A]
//
//val p = 5.pure[Logged]
//p.flatMap(w => {
//  System.out.println(w)
//  p
//})
//
//def returnFutureWriter(num: Int): Future[Writer[String, Int]] = {
//  Future.successful(num + 1).map(_.writer("Will add 1"))
//}
//
//def futureUnit(num: Int) = {
//  Future.successful(Unit)
//}
//
//
//val finalResult = for {
//  firstResult <- returnFutureWriter(4).pure[FutureWriterT]
//  _ <- futureUnit(5)
//} yield firstResult
//
//val s = finalResult.written






