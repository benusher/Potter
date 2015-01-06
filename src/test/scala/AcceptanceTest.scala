
class AcceptanceTest extends org.scalatest.FunSuite {
  test("no book costs nothing") {
    assert(0 === PricingService.price(0 :: Nil))
  }

  test("any one book costs 8€") {
    assert(8 === PricingService.price(1 :: Nil))
  }

  test("n copies of the same book costs n times the cost of one book (i.e. no discounts)") {
    assert(16 === PricingService.price(2 :: Nil))
    assert(24 === PricingService.price(3 :: Nil))
    assert(32 === PricingService.price(4 :: Nil))
    assert(40 === PricingService.price(5 :: Nil))
    assert(48 === PricingService.price(6 :: Nil))
  }

  test("If you buy all 5 books you get a 25% discount") {
    val basket: List[Int] = 1 :: 1 :: 1 :: 1 :: 1 :: Nil
    assert(30 === PricingService.price(basket))
  }

  test("If you buy 4 different books you get a 20% discount") {
    val basket: List[Int] = 1 :: 1 :: 1 :: 1 :: Nil
    assert(32 - 6.40 === PricingService.price(basket))
  }

  test("If you buy 3 different books you get a 10% discount") {
    val basket: List[Int] = 1 :: 1 :: 1 :: Nil
    assert(24 - 2.40 === PricingService.price(basket))
  }

  test("If you buy 2 different books you get a 5% discount") {
    val basket: List[Int] = 1 :: 1 :: Nil
    assert(16 - 0.80 === PricingService.price(basket))
  }
}
