class AcceptanceTest extends org.scalatest.FunSuite {
  test("An empty basket costs nothing") {
    assert(0 === PricingService.price(Nil))
  }

  test("A basket containing any one book costs 8€") {
    assert(8 === PricingService.price(0 :: Nil))
    assert(8 === PricingService.price(1 :: Nil))
    assert(8 === PricingService.price(2 :: Nil))
    assert(8 === PricingService.price(3 :: Nil))
    assert(8 === PricingService.price(4 :: Nil))
  }

  test("A basket containing several copies of the same book receives no discount") {
    assert(16 === PricingService.price(0 :: 0 :: Nil))
  }

  test("A basket of all 5 books receives a 25% discount") {
    val basket = 0 :: 1 :: 2 :: 3 :: 4 :: Nil
    assert(30 === PricingService.price(basket))
  }

  test("A basket of 4 different books receives a 20% discount") {
    val basket = 0 :: 1 :: 2 :: 3 :: Nil
    assert(25.60 === PricingService.price(basket))
  }

  test("A basket of 3 different books receives a 10% discount") {
    val basket = 0 :: 1 :: 2 :: Nil
    assert(21.60 === PricingService.price(basket))
  }

  test("A basket of 2 different books receives a 5% discount") {
    val basket = 0 :: 1 :: Nil
    assert(15.20 === PricingService.price(basket))
  }

  test("Discounts only apply to a set of different books") {
    val basket = 0 :: 0 :: 1 :: Nil
    assert(23.20 === PricingService.price(basket))
  }

  test("Group books into multiple sets to each attract a discount") {
    assert(30.40 === PricingService.price(0 :: 1 :: 0 :: 1 :: Nil))
   // assert(30.40 === PricingService.price(0 :: 0 :: 1 :: 1 :: Nil))
  }

  //  test("If you buy 4 books, 3 of which are different titles, you get a 10% discount on those 3 but the fourth book still costs 8€"){
  //    val basket: List[Int] = 0 :: 0 :: 1 :: 2 :: Nil
  //    assert(29.60 === PricingService.price(basket))
  //  }
  //

  //
  //  test("If you buy 4 books, 2 of which are different titles, you get a 5% discount on 2 books but the other two book still cost 8€ each"){
  //    val basket: List[Int] = 0 :: 0 :: 1 :: 1 :: Nil
  //    assert(31.20 === PricingService.price(basket))
  //  }

  //  test("If you buy 6 books, 4 of which are different titles, you can group such that you get a 20% discount on 4 and a 5% discount on two"){
  //    val basket: List[Int] = 0 :: 0 :: 1 :: 2 :: 2 :: 3 :: Nil
  //    assert(40.80 === PricingService.price(basket))
  //  }

  // assert_equal(8 + (8 * 5 * 0.75), price([0, 1, 1, 2, 3, 4]))
}
