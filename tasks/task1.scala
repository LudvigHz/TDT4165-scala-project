import scala.annotation.tailrec

object Main extends App {
  // Array containing values 1 to 50 generated using a for loop
  val oneToFifty: List[Int] = for { value <- (1 to 50).toList } yield value
  // Function that sums a list using a for loop
  def sumForLoop(ints: List[Int]) = {
    var sum: Int = 0
    for (int <- ints) {
      sum = sum + int
    }
    sum
  }
  // Function that sums a list using recursion
  @tailrec def sumRecursion(values: List[Int], partialSum: Int = 0): Int = {
    if (values.isEmpty) {
      partialSum
    } else {
      sumRecursion(values.tail, partialSum + values.head)
    }
  }

  /*
   * The reason for using BigInt is that fibonacci numbers may get quite large.
   * Thus BigInt is required in order to avoid overflow.
   * The difference between BigInt and Int is that BigInt can store larger numbers,
   * as the name would suggest
   *
   * This implementation will recursively calculate the previous 2 numbers in the sequence, which
   * means that the function will have a performance of O(2^n).
   * Using optimizations, we can reduce this to at least O(n)
   */
  def fib(N: Int): BigInt = {
    N match {
      case 0 => N
      case 1 => N
      case _ => fib(N - 1) + fib(N - 2)
    }
  }
}
