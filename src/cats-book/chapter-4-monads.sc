
import cats.Id

import scala.language.higherKinds

trait MyMonad[F[_]] {
  def pure[A](a: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] = flatMap(value)(a => pure(func(a)))
}

List(1, 2, 3).flatMap(i => List(i, i * 10))

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

def sumSquare[M[_] : Monad](a: M[Int], b: M[Int]): M[Int] =
  a.flatMap(x => b.map(y => x * x + y * y))

import cats.instances.list._
import cats.instances.option._

sumSquare(Option(3), Option(4))
sumSquare(List(1, 2, 3), List(4, 5, 6))


def sumSquare2[M[_] : Monad](a: M[Int], b: M[Int]): M[Int] =
  for {
    x <- a
    y <- b
  } yield x * x + y * y

sumSquare2(Option(3), Option(4))
sumSquare2(List(1, 2, 3), List(4, 5, 6))

sumSquare2(2: Id[Int], 2: Id[Int])

import cats.Id

def pure[A](a: A): Id[A] = a

def map[A, B](value: Id[A])(func: A => B): Id[B] = pure(func(value))

def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)


pure(123)

map(pure(1))(i => i + 1)
flatMap(pure(1))(i => i + 1)