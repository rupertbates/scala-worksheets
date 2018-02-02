import scala.util.Try

val t = Try(10)

val result = t.map((i: Int) => i > 5).getOrElse(false)
