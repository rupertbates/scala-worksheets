import io.circe.generic.semiauto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.{Decoder, Encoder}

case class User(firstName: String, lastName: String, age: Int = 0)

implicit val userDecoder: Decoder[User] = Decoder
  .forProduct3("firstName", "lastName", "age")(User.apply)
  .or(
    Decoder.forProduct2("firstName", "lastName")((a: String, b: String) => User(a, b))
  )
implicit val userEncoder: Encoder[User] = deriveEncoder

User("Foo", "McBar").asJson

val json =
  """
  {
    "firstName" : "Foo",
    "lastName" : "McBar",
    "age": "9"
  }
  """
val u = decode[User](json)
