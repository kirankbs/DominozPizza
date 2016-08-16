package com.dominoz.pizza

import org.junit.{Assert, Test}

/**
  * Created by kirankumar on 15-08-2016.
  */
class PizzaTest {

  @Test
  def checkMargheritaPizzaPrice(){
    Assert.assertEquals(150,pizzaFactory.get("Margherita").getPizzaPrice)
  }
  @Test
  def checkAvailableZestyPizzaPrice(){
    Assert.assertEquals(280,pizzaFactory.get("Zesty Chicken").getPizzaPrice)
  }
}
