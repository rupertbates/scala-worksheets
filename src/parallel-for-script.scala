import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps


object test {
  //val nums = printOut(List(1, 2, 3, 4, 5))
  val futureNums = Future {
    printOut(List(1, 2, 3, 4, 5))
  }
  //val letters = printOut(List("a", "b", "c", "d", "e"))
  val futureLetters = Future {
    printOut(List("a", "b", "c", "d", "e"))
  }

  def printOut[T](list: List[T]) =
    list.map(x => {
      println(x)
      Thread.sleep(100)
      x
    })

  def async(): Future[(List[Int], List[String])] = {
    println("Async")
    for {
      a <- futureNums
      b <- futureLetters
    } yield (a, b)
  }

//  def sync(): Future[(List[Int], List[String])] ={
//    println("Sync")
//    for {
//      a <- Future {
//        nums
//      }
//      b <- Future {
//        letters
//      }
//    } yield (a, b)
//  }
}

Await.result(test.async(), 10 seconds)
//Await.result(test.sync(), 20 seconds)

//test.doIt().map(x => println(x))

val result = (1 to 12).fold(0)((acc, x) => acc + x)


