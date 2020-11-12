object Task2 extends App {
  // Function for task a)
  def threadFunction(func: () => Unit): Thread = {
    new Thread {
      override def run: Unit = {
        func()
      }
    }
  }

  private var counter: Int = 0
  def increaseCounter(): Unit = {
    counter += 1
  }

  // b)
  def printCounter(): Unit = {
    println(this.counter)
  }

  /*
   * val thread1: Thread = threadFunction(increaseCounter)
   * val thread2: Thread = threadFunction(increaseCounter)
   * val printThread: Thread = threadFunction(printCounter)
   *
   * thread1.start()
   * printThread.start() // should print 1, but can print 2 if threadfunction is not thread safe
   * thread2.start()
   *
   * the above procedure can cause a race condition, which is when the
   * results of an operation depends on the timing of events outside of our control.
   * here this happens because the value printed depends
   * on the order of execution for the thread operations, which is non-deterministic.
   * This is problematic if you have two threads who want to withdraw all the money from an account.
   * If you are unlucky, both will check that the balance is equal to the amount
   * that will be withdrawn, before any of them can withdraw money from the account.
   * The two threads will then both withdraw an amount equal to the entire balance,leading to the account
   * having a negative balance, which might be an illegal state.
   */

  // c) Thread safe increaseCounter
  def increaseCounterSafe(): Unit = counter.synchronized {
    counter += 1
  }
  // Safe threads
  val thread1: Thread = threadFunction(increaseCounterSafe)
  val thread2: Thread = threadFunction(increaseCounterSafe)
  val printThread: Thread = threadFunction(printCounter)

  thread1.start()
  // will still produce a non-deterministic result (race condition)
  printThread.start()
  thread2.start()

  /*
   * d)
   *
   * A deadlock is a situation where a several threads wait forever,
   * because they each wait for the lock on a variable held by another
   * of the waiting threads.
   *
   * This can be avoided by having a total ordering on
   * each lock being aquired, so that a thread cannot try to aquire a resource while
   * waiting for a another thread to relinquish control of a resource.
   *
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
