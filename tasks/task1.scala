// TODO: task 1
object Main extends App {
  val generateValues = (1 to 50).toList
  def sumForLoop(ints: List[Int]) = {
    var sum: Int = 0
    for (int <- ints) {
      sum = sum + int
    }
    sum
  }
  def sumRecursion(values: List[Int], partialSum: Int): Int = {
    if (values.isEmpty) {
      partialSum
    } else {
      funcC(values.tail, partialSum + values.head)
    }
  }
  // The reason for using BigInt is that factorials tend to get quite large very quickly
  // Thus BigInt is required in order to avoid overflow
  def fib(N: Int): BigInt = {
    def fibTailRec(N: Int, accumulator: BigInt): BigInt = {
      if (N == 0) {
        accumulator
      } else {
        fibTailRec(N - 1, accumulator * N)
      }
    }
    fibTailRec(N, 1)
  }
}
