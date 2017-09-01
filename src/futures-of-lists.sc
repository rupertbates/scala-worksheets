import cats.Monad
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

val l = List("a", "b", "c")
val fl = Future {
  l
}

def listOfList(s: String) = Future {
  List(s, s, s)
}

val fr = for {
  mapped <- fl.flatMap{ x => Monad[Future].pure(x.map(listOfList)) }
  combined <- mapped.combineAll
} yield combined.contains("a")

val mapped = fl.flatMap{ x => Monad[Future].pure(x.map(listOfList)) }
val combined = mapped.flatMap(_.combineAll)
val exists = combined.map{y : List[String]=> y.contains("a")}

val result = Await.result(fr, 10.seconds)







