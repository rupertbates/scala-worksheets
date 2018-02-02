val x = 10
def logWithBase(base: Double, num: Double) = scala.math.log10(num) / scala.math.log10(base)

val logBase3Of9 = logWithBase(3, 9)
