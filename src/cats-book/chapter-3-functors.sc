import scala.util.Try

/*
Formally, a functor is a type F[A] with an operation map with type (A => B) => F[B]
 */


//Contramap
trait Printable[A] {
  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] = {
    val self = this
    new Printable[B] {
      override def format(value: B): String = self.format(func(value))
    }
  }
}

def format[A](value: A)(implicit p: Printable[A]): String =
  p.format(value)

implicit val stringPrintable =
  new Printable[String] {
    def format(value: String): String =
      "\"" + value + "\""
  }

implicit val booleanPrintable =
  new Printable[Boolean] {
    def format(value: Boolean): String =
      if (value) "yes" else "no"
  }

format("test")

format(true)

final case class Box[A](value: A)

implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] = {
  p.contramap[Box[A]](_.value) //generate a Printable[Box[A]] from a Printable[A] using contramap
}

format(Box("box"))

//Imap

trait Codec[A] {
  def encode(value: A): String

  def decode(value: String): Option[A]

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this
    new Codec[B] {
      override def encode(value: B) = self.encode(enc(value))

      override def decode(value: String) = self.decode(value).map(dec)
    }
  }
}

implicit val intCodec = new Codec[Int] {
  override def encode(value: Int) = value.toString

  override def decode(value: String): Option[Int] = Try(value.toInt).toOption
}

def encode[A](value: A)(implicit c: Codec[A]): String =
  c.encode(value)
def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
  c.decode(value)

encode(123)
decode("456")
decode("seven")

implicit def boxCodec[Int](implicit c: Codec[Int]) = {
  c.imap(value => Box(value), (box : Box[Int]) => box.value) //generate a Codec for Box[Int] from a Codec[Int] and imap
}

encode(Box(123))
decode[Box[Int]]("456")
