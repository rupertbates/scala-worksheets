import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def printOut[T](list : List[T]) = {
  Future{
    list.foreach(x =>
    {
      println(x)
      Thread.sleep(1000)
    })
  }
}

def doIt() = {
  for {
    a <- printOut(List(1, 2, 3, 4, 5))
    b <- printOut(List("a", "b", "c", "d", "e"))
  } yield b
}


doIt()














