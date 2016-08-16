package com.dominoz.pizza.ingredient

import com.dominoz.pizza.{ToppingsFactory, Crust, Empty, Topping}
import org.junit.{Assert, Test}

/**
  * Created by kirankumar on 15-08-2016.
  */
class IngredientsTest {

  trait Test1{
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
      .incl(new Topping("Smoked Chicken",150,2))
  }

  trait Test2{
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
  }

  trait Test3{
    val emptyTops= new Empty
    val tops = emptyTops.incl(new Topping("Tomato",10))
      .incl(new Topping("Baby Corn",10))
      .incl(new Topping("Pineapple",20))
      .incl(new Topping("Jalapeno",15))
      .incl(new Topping("Chicken Salami",95))
      .incl(new Topping("Pineapple",20))
      .incl(new Topping("Capsicum",10))
      .incl(new Topping("Paprika",30))
      .incl(new Topping("Panner",80))
      .incl(new Topping("Chicken Sausage",110))
      .incl(new Topping("Barbeque Chicken",100))
      .incl(new Topping("Spicy Chicken",90))
      .incl(new Topping("Panner",80))
      .incl(new Topping("Smoked Chicken",150))
      .incl(new Topping("Smoked Chicken",150))
  }

  @Test
  def getToppingPrice(){
    new Test1 {
      Assert.assertEquals(110,tops.price(to => to.name.equalsIgnoreCase("Chicken Sausage")))
      Assert.assertEquals(10,tops.price(to => to.name.equalsIgnoreCase("Tomato")))
      Assert.assertEquals(150,tops.price(to => to.name.equalsIgnoreCase("Smoked Chicken")))
    }
  }

  @Test
  def getToppingsTotalPrice(){
    new Test1 {
      Assert.assertEquals(915,tops.totalToppingsPrice())
    }
  }


  @Test
  def removeTopping{
    val emptyTops= new Empty
    val expected = emptyTops.incl(new Topping("Tomato",10))
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

    Assert.assertTrue(new Test1 {}.tops.remove(tw => tw.name.equalsIgnoreCase("Smoked Chicken")).contains(new Topping("Tomato",80)))
    Assert.assertTrue(new Test1 {}.tops.remove(tw => tw.name.equalsIgnoreCase("Smoked Chicken")).contains(new Topping("Panner",80)))
    Assert.assertTrue(new Test1 {}.tops.remove(tw => tw.name.equalsIgnoreCase("Smoked Chicken")).contains(new Topping("Cheese",80)))
    Assert.assertFalse(new Test1 {}.tops.remove(tw => tw.name.equalsIgnoreCase("Smoked Chicken")).contains(new Topping("Smoked Chicken",150)))
  }

  @Test
  def getTotalToppingsPriceAfterRemoval{
    new Test2 {
      Assert.assertEquals(670,tops.remove(tw => tw.name.equalsIgnoreCase("Panner"))
        .remove(tw => tw.name.equalsIgnoreCase("Jalapeno"))
        .totalToppingsPrice())}
  }

  @Test
  def getTotalToppingsPriceAfterReducingToppingQuantity{
    new Test3 {
      Assert.assertEquals(470,tops.remove(tw => tw.name.equalsIgnoreCase("Panner"))
        .remove(tw => tw.name.equalsIgnoreCase("Pineapple"))
        .remove(tw => tw.name.equalsIgnoreCase("Smoked Chicken"))
        .totalToppingsPrice())}
  }

  @Test
  def getTotalPriceByAddingDuplicateTooppings{
    new Test1 {
      Assert.assertEquals(955,tops.
        incl(new Topping("Tomato",ToppingsFactory.get("Tomato").price,2)).
        incl(new Topping("Paprika",ToppingsFactory.get("Paprika").price,2)).
        totalToppingsPrice())
    }

  }


  @Test
  def getCrustPrice{
    Assert.assertEquals(150,Crust.get("Thin Crust").price)
  }

}
