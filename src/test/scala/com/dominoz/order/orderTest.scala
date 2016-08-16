package com.dominoz.order

import com.dominoz.pizza.{Crust, ToppingsFactory, pizzaFactory, Pizza}
import org.junit.{Assert, Test}

/**
  * Created by kirankumar on 15-08-2016.
  */
class orderTest {

  @Test
  def checkOrderServiceTax(){
    Assert.assertEquals(124,Tax.getServiceTax(15,830))
  }

  @Test
  def checkOrderVat(){
    Assert.assertEquals(83,Tax.getVat(10,830))
  }

  @Test
  def testOrder1(){
    //given
    val veggieParadise: Pizza = pizzaFactory.get("Veggie Paradise")
    val chickenMexicana1: Pizza = pizzaFactory.get("Chicken Maxicana")
    val chickenMexicana2: Pizza = pizzaFactory.get("Chicken Maxicana")
    //when
    val order: Order = new Order(chickenMexicana1 :: chickenMexicana1 :: veggieParadise :: Nil)
    //then
    Assert.assertEquals(1037.5,order.orderPrice,1)
  }

  @Test
  def testOrder2(){
    //given
    val  hawaiianDelightVeg: Pizza = pizzaFactory.get("Hawaiian Delight Veg").
      addToppings(ToppingsFactory.get("Olives")).
      addToppings(ToppingsFactory.get("Cheese")).
      changeCrust(Crust.get("Pan Pizza"))

    val  hawaiianDelightNonVeg: Pizza = pizzaFactory.get("Hawaiian Delight NonVeg").
      addToppings(ToppingsFactory.get("Barbeque Chicken")).
      changeCrust(Crust.get( "Cheese Burst"))

    //when
    val order: Order = new Order(hawaiianDelightVeg :: hawaiianDelightNonVeg :: Nil)
    //then
    Assert.assertEquals(1068.75,order.orderPrice,1)
  }
}
