package com.dominoz.order

import com.dominoz.pizza.{pizzaFactory, Pizza}

/**
  * Created by kirankumar on 15-08-2016.
  */
object OrderMaker extends App{

  val margherita: Pizza = pizzaFactory.get("Margherita")

  val zestyChicken: Pizza = pizzaFactory.get("Zesty Chicken")

  val order: Order = new Order(zestyChicken :: margherita :: Nil)

  println(order.orderPrice)
}
