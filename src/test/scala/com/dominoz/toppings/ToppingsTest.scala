package com.dominoz.toppings

import org.junit.{Assert, Test}

/**
  * Created by kirankumarbs on 8/12/2016.
  */
class ToppingsTest {

  trait TestToppingsTest{
    val emptyTops= new Empty
    val tops = emptyTops.incl(new Topping("Tomato",10))
                        .incl(new Topping("Baby Corn",10))
                        .incl(new Topping("Pineapple",20))
                        .incl(new Topping("Jalapeno",15))
                        .incl(new Topping("Chicken Salami",95))
                        .incl(new Topping("Olives",15))
                        .incl(new Topping("Capsicum",10))
                        .incl(new Topping("Paprika",30))
                        .incl(new Topping("Panner",80))
                        .incl(new Topping("Chicken Sausage",110))
                        .incl(new Topping("Barbeque Chicken",100))
                        .incl(new Topping("Spicy Chicken",90))
                        .incl(new Topping("Onion",10))
                        .incl(new Topping("Cheese",20))
                        .incl(new Topping("Smoked Chicken",150))
                        .incl(new Topping("Smoked Chicken",150))
  }

  @Test
  def getToppingPrice: Unit ={
    new TestToppingsTest {
      Assert.assertEquals(110,tops.price(to => to.name.equalsIgnoreCase("Chicken Sausage")))
      Assert.assertEquals(10,tops.price(to => to.name.equalsIgnoreCase("Tomato")))
      Assert.assertEquals(150,tops.price(to => to.name.equalsIgnoreCase("Smoked Chicken")))
    }
  }

  @Test
  def getToppingsTotalPrice: Unit ={
    new TestToppingsTest {
      Assert.assertEquals(765,tops.totalToppingsPrice())
    }
  }

}
