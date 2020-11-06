object Main extends App {

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start
    t
  }
  var failed = 0
  val bank = new Bank(allowedAttempts = 1)

  val acc1 = new Account(bank, 100)
  val acc2 = new Account(bank, 100)
  val acc3 = new Account(bank, 100)
  acc1 transferTo (acc2, 50)

}
