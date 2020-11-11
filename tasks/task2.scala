object Task2 extends App {
  private var counter: Int = 0
  def increaseCounter(): Unit = {
    counter += 1
  }
  def printCounter(): Unit = {
    println(this.counter)
  }
  // Function for task a)
  def threadFunction(func: () => Unit): Thread = {
    new Thread {
      override def run: Unit = {
        func()
      }
    }
  }
  // Unsafe threads
  // These threads cause a race condition, which is when the results of an operation
  // depends on the timing of events outside of our control. Here this happens as the value printed depends
  // on the order of execution for the thread operations, which varies.
  /*
  val thread1: Thread = threadFunction(increaseCounter)
  val printThread: Thread = threadFunction(printCounter)
  val thread2: Thread = threadFunction(increaseCounter)
   */
  // Thread safe increaseCounter
  def increaseCounterSafe(): Unit = counter.synchronized {
    counter += 1
  }
  // Safe threads
  val thread1: Thread = threadFunction(increaseCounterSafe)
  val thread2: Thread = threadFunction(increaseCounterSafe)
  val printThread: Thread = threadFunction(printCounter)
  thread1.start()
  printThread
    .start() // Should print 1, but can print 2 if increaseCounter is not thread safe
  thread2.start()
  /*
   * A deadlock is a situation where a several threads wait forever, as they each wait for the lock on
   * another variable held by another of the waiting threads. It can be avoided by having a total ordering on
   * each lock being aquired, so that a thread cannot try to aquire a resource while
   * waiting for a another thread to relinquish control of a resource.
   */

  // Function that causes a deadlock by utilizing lazy variables
  def lazyDeadlock(): Unit = {
    object testA { lazy val test: Int = testB.test }
    object testB { lazy val test: Int = testA.test }
    val thread1 = threadFunction(() => testA.test)
    val thread2 = threadFunction(() => testB.test)
    thread1.start()
    thread2.start()
  }
}
