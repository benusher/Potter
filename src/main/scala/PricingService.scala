import scala.annotation.tailrec

object PricingService {
  type Basket = List[Int]

  def price(basket: Basket): BigDecimal = {
    def discountedPrice(numberOfDifferentBooks: Int): BigDecimal = numberOfDifferentBooks match {
      case 5 => price * BigDecimal(0.75)
      case 4 => price * BigDecimal(0.8)
      case 3 => price * BigDecimal(0.9)
      case 2 => price * BigDecimal(0.95)
      case _ => price * BigDecimal(1.0)
    }
//list.filterNot(list)
    val numberOfDifferentBooks: Int = basket.distinct.size
    totUp(0, 0, basket, numberOfDifferentBooks, discountedPrice(numberOfDifferentBooks))
  }

  @tailrec
  def totUp(acc: Int, runningTotal: BigDecimal, basket: Basket, numberOfDifferentBooks: Int, reducedPrice: BigDecimal): BigDecimal = {
    def getPrice: BigDecimal = if (numberOfDifferentBooks - acc > 0) reducedPrice else price

    if (basket.isEmpty)
      format(runningTotal)
    else {
      totUp(acc + 1, runningTotal + getPrice, basket.tail, numberOfDifferentBooks, reducedPrice)
    }
  }

  def format(runningTotal: BigDecimal): BigDecimal = {
    runningTotal.setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  private val price: BigDecimal = 8
}
