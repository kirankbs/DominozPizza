package com.dominoz.order

import com.dominoz.pizza.Pizza

/**
  * Created by kirankumar on 15-08-2016.
  */
class Order(val pizzaList: List[Pizza] = Nil) {
  def addPizza(pizza: Pizza): List[Pizza] = pizza :: pizzaList
  def removePizza(pizza:Pizza): List[Pizza] = pizzaList.filterNot(p => pizza.name.equalsIgnoreCase(p.name))
  def orderPrice: Int = {
    def orderAcc(pizzas: List[Pizza], acc: Int): Int =
      if (pizzas.isEmpty) acc else orderAcc(pizzas.tail, acc + pizzas.head.getPizzaPrice)
    val p = orderAcc(pizzaList, 0)
    p + Tax.getServiceTax(15,p) + Tax.getVat(10,p)
  }

}
