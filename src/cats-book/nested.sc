import cats.data._
import cats.implicits._
import cats.instances._

import scala.concurrent.{Await, Awaitable, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
def await[T](a:Awaitable[T]) = Await.result(a, 1000 seconds)

// NOTE: all the errors in this file are IntelliJ just not understanding
// the syntax, probably due to the sbt plugin this requires to work
// see: project/plugins.sbt

val l = List(1,2,3)
val e: Either[String, List[Int]] = Right(l)
val fel: Future[Either[String, List[Int]]] = Future.successful(e)

val nfel = Nested(fel)

//Only maps through to List level not individual items
val mapped = nfel.map(_.toString + "blah")
val result = await(mapped.value)
result.right.get //A String


//Without the future it maps through to individual elements
val nel = Nested(e)
val mapped2 = nel.map(_ + 1)



