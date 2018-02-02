import scala.math.log10
import scala.math.pow

val x = 10

def logBase(base: Double, num: Double) = log10(num) / log10(base)
def factorial(n: Double): Double = if (n == 0) 1 else n * factorial(n - 1)
def mChooseN(m: Double, n: Double) = factorial(m) / (factorial(m - n) * factorial(n))

factorial(10)
factorial(5)
mChooseN(10, 5)


// What is the chance of flipping heads exactly 72 times
// out of 100 with a fair coin

val n = 100D //number of coin tosses
val s = 72D //number of successes
val p = 0.5D //probability of flipping heads (success)


val result = mChooseN(n, s) * pow(p, s) * pow(1 - p, n - s)
// ^ = v
(factorial(100) / (factorial(28) * factorial(72))) * (pow(.5, 72) * pow(.5, 28))

