import scala.annotation.tailrec

object Main extends App {
  val oneToFifty: List[Int] = for { value <- (1 to 50).toList } yield value
  def sumForLoop(ints: List[Int]) = {
    var sum: Int = 0
    for (int <- ints) {
      sum = sum + int
    }
    sum
  }
  @tailrec def sumRecursion(values: List[Int], partialSum: Int = 0): Int = {
    if (values.isEmpty) {
      partialSum
    } else {
      sumRecursion(values.tail, partialSum + values.head)
    }
  }
  // The reason for using BigInt is that fibonacci numbers tend to get quite large very quickly
  // Thus BigInt is required in order to avoid overflow
  def fib(N: Int): BigInt = {
    if (N == 0) {
      N
    } else if (N == 1) {
      N
    } else {
      fib(N - 1) + fib(N - 2)
    }
  }
}
