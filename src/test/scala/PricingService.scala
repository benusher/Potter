
object PricingService {
  private val price: Int = 8

  def price(basket: List[Int]): Double = {
    if (basket.isEmpty)
      0
    else {
      val total = basket.foldLeft(0)((acc, copies) => acc + (copies * price))

      total * getDiscount(basket.size)
    }
  }

  def getDiscount(numberOfDifferentBooks: Int) = numberOfDifferentBooks match {
    case 5 =>.75
    case _ => 1.0
  }
}
