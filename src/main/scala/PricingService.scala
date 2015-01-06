import scala.annotation.tailrec

object PricingService {
  private val price = 8
  def price(basket: List[Int]): Double = {
    totUp(0, basket, basket.size)
  }

  @tailrec
  def totUp(runningTotal: BigDecimal, basket: List[Int], numberOfDifferentBooks: Int) :Double = {
    if(basket.isEmpty)
      runningTotal.setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    else {
      val result: BigDecimal = ((basket.head - 1) * price) + (1 * discountedPrice(numberOfDifferentBooks))
      totUp(runningTotal + result, basket.tail, numberOfDifferentBooks)
    }
  }
  
  def discountedPrice(numberOfDifferentBooks: Int): BigDecimal = numberOfDifferentBooks match {
    case 5 => price * 0.75
    case 4 => price * 0.8
    case 3 => price * 0.9
    case 2 => price * 0.95
    case _ => price * 1.0
  }
}
