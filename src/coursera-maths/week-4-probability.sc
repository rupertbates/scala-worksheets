import scala.math.pow

//Necessary functions
def factorial(n: Double): Double = if (n == 0) 1 else n * factorial(n - 1)
def mChooseN(m: Double, n: Double) = factorial(m) / (factorial(m - n) * factorial(n))

factorial(10)
factorial(5)
mChooseN(10, 5)

// What is the chance of flipping heads exactly 72 times
// out of 100 with either a fair or a bent coin

val n = 100D //number of coin tosses
val s = 72D //number of successes
val p = 0.5D //probability of flipping heads with fair coin (success)
val b = 0.55D //probability of flipping heads with bent coin (success)

def probabilityOfSfromN(s: Double, n: Double, p: Double)
= mChooseN(n, s) * pow(p, s) * pow(1 - p, n - s)
// The above is equal to:
(factorial(100) / (factorial(28) * factorial(72))) * (pow(.5, 72) * pow(.5, 28))

//The probablity of doing it with a fair coin is:
val fairCoin = probabilityOfSfromN(s, n, p)
//The probability of doing it with a bent coin is:
val bentCoin = probabilityOfSfromN(s, n, b)

//Therefore if we do it with one of the two coins and we want to know which,
//the probability that it was the fair coin is:
val probabilityOfFairCoin = fairCoin * p / (fairCoin * p + bentCoin * p)

//And the probability of the bent coin is
val probabilityOfBentCoin = 1 - probabilityOfFairCoin

//Question 3. If a coin is bent so that it has a 40% probability of coming up heads,
//what is the probability of getting exactly 6 heads in 10 throws?
probabilityOfSfromN(6, 10, 0.4)

//Question 4. A bent coin has 40% probability of coming up heads on each independent toss.
// If I toss the coin ten times, what is the probability that I get at least 8 heads?

// This is the probability of it coming up 8 times OR 9 times OR 10 times
val p8 = probabilityOfSfromN(8, 10, 0.4)
val p9 = probabilityOfSfromN(9, 10, 0.4)
val p10 = probabilityOfSfromN(10, 10, 0.4)

val result = (p8 + p9 + p10) - (p8 * p9 * p10)

// Generalise this to:
def atLeastSFromN(s: Double, n: Double, p: Double) = {
  val probabilities = s.to(n, 1)
    .map(probabilityOfSfromN(_, n, p))
  probabilities.sum - probabilities.product
}

atLeastSFromN(8, 10, 0.4) == result

//Question 5. Suppose I have a bent coin with a 60% probability of coming up heads.
//I throw the coin ten times and it comes up heads 8 times.
//What is the value of the “likelihood” term in Bayes’ Theorem -- the conditional
//probability of the data given the parameter.
probabilityOfSfromN(8, 10, 0.6)



