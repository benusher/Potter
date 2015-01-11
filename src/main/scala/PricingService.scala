import scala.annotation.tailrec

object PricingService {
  type Basket = List[Int]
  type GroupsOfBooks = List[List[Int]]
  type Combinations = List[List[Int]]

  def price(basket: Basket): BigDecimal = {
    val possibleGroupings = getGroupOfBooks(Nil, basket)
    possibleGroupings.foldLeft(BigDecimal(0))((total, nextGroup) => total + priceWithDiscounts(nextGroup) )
  }

  @tailrec
  def getGroupOfBooks(acc: GroupsOfBooks, remainingBasket: Basket): GroupsOfBooks = {
    if (remainingBasket.isEmpty)
      acc
    else{
      val nextGroup = getPossibleGroupings(remainingBasket).head
      val newRemainingBasket = remainingBasket diff nextGroup
      getGroupOfBooks(nextGroup:: acc, newRemainingBasket)
    }
  }

  def getPossibleGroupings(basket: Basket): List[List[Int]] = {
    val allGroupings = for {
      i <- 1 to basket.distinct.length
      combinations <- basket combinations i
    } yield combinations
    allGroupings.filter(group => group.size == group.distinct.size).toList
  }

  def priceWithDiscounts(basket: List[Int]): BigDecimal = {
    val noOfDifferentBooks: Int = basket.size
    val discount = noOfDifferentBooks match {
      case 5 => .75
      case 4 => .8
      case 3 => .9
      case 2 => .95
      case _ => 1
    }
    price * noOfDifferentBooks * discount
  }

  private val price = BigDecimal(8)
}
