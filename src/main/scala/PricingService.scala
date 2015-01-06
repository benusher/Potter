
object PricingService {
  def price(basket: List[Int]): Double = {
    if (basket.isEmpty)
      0
    else applyDiscount(total(basket), basket.size)
  }

  def total(basket: List[Int]): Int = {
    basket.foldLeft(0)((acc, copies) => acc + (copies * 8))
  }

  def applyDiscount(total: Int, numberOfDifferentBooks: Int) = numberOfDifferentBooks match {
    case 5 => total * 0.75
    case 4 => total * 0.8
    case 3 => total * 0.9
    case 2 => total * 0.95
    case _ => total * 1.0
  }
}
