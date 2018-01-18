import math.sqrt

case class Point(x: Double, y: Double)

def square(x: Double) = x * x

//Distance
def distance(a: Point, b: Point) = sqrt(square(a.x - b.x) + square(a.y - b.y)) //Order is not important

val a = Point(2, 2)
val b = Point(-1, -2)


val dist = distance(a, b)


//Slope
def slope(a: Point, b: Point) = (b.y - a.y) / (b.x - a.x) //Order is not important

val c = Point(1, 2)
val d = Point(3, 3)

val m = slope(c, d)
val m2 = slope(Point(-1, 1), Point(0, 0))
val m3 = slope(Point(2, 1), Point(3, 2))
val m4 = slope(Point(1, 1), Point(5, 3))


//Point slope formula
def isPointOnLine(unknown: Point, known: Point, slope: Double) =
  unknown.y - known.y == slope * (unknown.x - known.x)

isPointOnLine(Point(3, 2), Point(2, 1), 1)
isPointOnLine(Point(5, 1), Point(2, 1), 1)


//Point slope intercept formula - find b where b is the point
//on the y axis where the line crosses
def findYInterceptPoint(known: Point, slope: Double) =
  slope * (0 - known.x) + known.y

def known = Point(-1, 0)

findYInterceptPoint(Point(2, 1), 1)
findYInterceptPoint(known, 2)

isPointOnLine(Point(0, 2), known, 2)

def findYInterceptPointForFormula(f: Int => Int) = {
  val points = getPointsForFormula(f)
  val s = slope(points.head, points(1))
  findYInterceptPoint(points.head, s)
}

val ip = findYInterceptPointForFormula(x => -3 * x + 2)
val ip2 = findYInterceptPointForFormula(x => 5 * x + 2)

//Get points by formula
def formula(x: Int) =  -3 * x + 2
def formula2(x: Int) = -3 * x - 8
def applyFormula(f: Int => Int)(x: Int) = Point(x, f(x))

def getPointsForFormula(f: Int => Int) = (1 to 2).map(applyFormula(f)).toList

def points = getPointsForFormula(formula)
val formulaSlope = slope(points(0), points(1))


