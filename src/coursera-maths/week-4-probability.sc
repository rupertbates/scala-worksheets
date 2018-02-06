import scala.math.pow

//Necessary functions
def factorial(n: Double): Double = if (n == 0) 1 else n * factorial(n - 1)
def mChooseN(m: Double, n: Double) = factorial(m) / (factorial(m - n) * factorial(n))


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

/*Question 1. A jewelry store that serves just one customer at a time is concerned about the safety of its isolated customers.
The store does some research and learns that:

10% of the times that a jewelry store is robbed, a customer is in the store.
A jewelry store has a customer on average 20% of each 24-hour day.
The probability that a jewelry store is being robbed (anywhere in the world) is 1 in 2 million.

What is the probability that a robbery will occur while a customer is in the store?

What is known is:
A: "a customer is in the store," P(A)=0.2
B: "a robbery is occurring," P(B)=12,000,000
P(a customer is in the store∣a robbery occurs)=P(A∣B)
P(A∣B) = 10%
  What is wanted:
  P(a robbery occurs∣a customer is in the store)=P(B∣A)
By the product rule:
  P(B∣A)=P(A,B)P(A)
and P(A,B)=P(A∣B)P(B)
Therefore:
  P(B∣A)=P(A∣B)*P(B)/P(A)=(0.1)* 120000000 / 2 = 1 / 4000000
*/


val pA = 0.2 //A jewelry store has a customer on average 20% of each 24-hour day.
val pB = 1d / 2000000 //The probability that a jewelry store is being robbed (anywhere in the world) is 1 in 2 million.
val pAGivenB = 0.1 //10% of the times that a jewelry store is robbed, a customer is in the store.

val pBGivenA = (pAGivenB * pB) / pA

//2. Question 2
//If I flip a fair coin, with heads and tails, ten times in a row, what is the
// probability that I will get exactly six heads?
val answer2 = probabilityOfSfromN(6, 10, 0.5)

//Question 3. If a coin is bent so that it has a 40% probability of coming up heads,
//what is the probability of getting exactly 6 heads in 10 throws?
val answer3 = probabilityOfSfromN(6, 10, 0.4)

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


//Question 6
//We have the following information about a new medical test for diagnosing cancer.
//Before any data are observed, we know that 5% of the population to be tested actually have Cancer.
//  Of those tested who do have cancer, 90% of them get an accurate test result of “Positive” for cancer.
// The other 10% get a false test result of “Negative” for Cancer.
//  Of the people who do not have cancer, 90% of them get an accurate test result of “Negative” for cancer.
// The other 10% get a false test result of “Positive” for cancer.
//  What is the conditional probability that I have Cancer, if I get a “Positive” test result for Cancer?
//
//Posterior probability:
//  p(I actually have cancer | receive a “positive” Test)
//By Bayes Theorem:
//=(chance of observing a PT if I have cancer) * (prior probability of having cancer) / (marginal likelihood of the observation of a PT)
//=p(receiving positive test| has cancer) * p(has cancer [before data is observed])p(positive | has cancer) / p(has cancer)+p(positive | no cancer ) + p(no cancer)
//  = (90%)(5%) / ((90%)(5%) + (10%)(95%)
//  =32.1%

var likelihoodHaveWithPT =   0.9
var likelihoodHaventWithPT = 0.1
var priorProbabilityHave = 0.05
var priorProbabilityHavent = 0.95

val answer6 = (likelihoodHaveWithPT * priorProbabilityHave) /
  (likelihoodHaveWithPT * priorProbabilityHave + likelihoodHaventWithPT * priorProbabilityHavent)

// Question 7
// We have the following information about a new medical test for diagnosing cancer.
// Before any data are observed, we know that 8% of the population to be tested actually have Cancer.

// Of those tested who do have cancer, 90% of them get an accurate test result of "Positive'' for cancer.
// The other 10% get a false test result of "Negative'' for Cancer.

// Of the people who do not have cancer, 95% of them get an accurate test result of "Negative'' for cancer.
// The other 5% get a false test result of "Positive'' for cancer.

// What is the conditional probability that I have cancer, if I get a "Negative'' test result for Cancer?

val likelihoodHaveWithNT = 0.1
val likelihoodHaventWithNT = 0.95
priorProbabilityHave = 0.08
priorProbabilityHavent = 0.92

val answer7 = (likelihoodHaveWithNT * priorProbabilityHave) /
  (likelihoodHaveWithNT * priorProbabilityHave + likelihoodHaventWithNT * priorProbabilityHavent)

