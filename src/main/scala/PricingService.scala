import scala.annotation.tailrec

object PricingService {
  type Basket = List[Int]

  def price(basket: Basket): Double = {
    def discountedPrice(numberOfDifferentBooks: Int): BigDecimal = numberOfDifferentBooks match {
      case 5 => price * 0.75
      case 4 => price * 0.8
      case 3 => price * 0.9
      case 2 => price * 0.95
      case _ => price * 1.0
    }

    totUp(0, 0, basket, basket.toSet.size, discountedPrice(basket.toSet.size))
  }

  @tailrec
  def totUp(acc: Int, runningTotal: BigDecimal, basket: Basket, numberOfDifferentBooks: Int, reducedPrice: BigDecimal): Double = {
    def getPrice: BigDecimal = if (numberOfDifferentBooks - acc > 0) reducedPrice else price

    if (basket.isEmpty)
      format(runningTotal)
    else {
      totUp(acc + 1, runningTotal + getPrice, basket.tail, numberOfDifferentBooks, reducedPrice)
    }
}

def format(runningTotal: BigDecimal): Double = {
  runningTotal.setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
}

private val price: BigDecimal = 8
}
