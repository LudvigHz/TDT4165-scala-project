import exceptions._
import scala.Either

class Account(val bank: Bank, initialBalance: Double) {

  class Balance(var amount: Double) {}

  val balance = new Balance(initialBalance)

  // TODO
  // for project task 1.2: implement functions
  // for project task 1.3: change return type and update function bodies
  def withdraw(amount: Double): Either[Double, String] = {
    balance.synchronized {
      if (amount < 0) {
        Right("Cannot withdraw negative value")
      } else if (balance.amount < amount) {
        Right("Insufficient funds")
      } else {
        balance.amount -= amount
        Left(amount)
      }
    }
  }

  def deposit(amount: Double): Either[Double, String] = {
    balance.synchronized {
      if (amount < 0) {
        Right("cannot deposit negative value")
      } else {
        balance.amount += amount
        Left(amount)
      }

    }
  }

  def getBalanceAmount: Double = balance.synchronized {
    balance.amount
  }

  def transferTo(account: Account, amount: Double) = {
    bank addTransactionToQueue (this, account, amount)
  }

}
