package com.dominoz.pizza

/**
  * Created by kirankumar on 15-08-2016.
  */
class Pizza(val name: String,val toppings: ToppingSet,val crust: Crust,val price: Int) {
  def addToppings(top: Topping): Pizza = new Pizza(name,toppings.incl(top),crust,price)
  def removeToppings(top: Topping): Pizza = new Pizza(name,toppings.remove(tw => tw.name.equalsIgnoreCase(top.name)),crust,price)
  def changeCrust(c: Crust): Pizza = new Pizza(name,toppings,c,price)
  def getPizzaPrice: Int = price + toppings.totalToppingsPrice() + crust.price
}

object pizzaFactory{
  private val pizzas = Map(
    "Margherita" -> new Pizza("Margherita",
          new Empty().incl(new Topping("Tomato",ToppingsFactory.get("Tomato").price,0)),
          Crust.get("Regular"),150),
    "Hawaiian Delight Veg" -> new Pizza("Hawaiian Delight",
          new Empty()
            .incl(new Topping("Baby Corn",ToppingsFactory.get("Baby Corn").price,0))
            .incl(new Topping("Pineapple",ToppingsFactory.get("Pineapple").price,0))
            .incl(new Topping("Jalapeno",ToppingsFactory.get("Jalapeno").price,0)),
          Crust.get("Regular"),200),
    "Hawaiian Delight NonVeg" -> new Pizza("Hawaiian Delight",
          new Empty()
            .incl(new Topping("Chicken Salami",ToppingsFactory.get("Chicken Salami").price,0))
            .incl(new Topping("Pineapple",ToppingsFactory.get("Pineapple").price,0))
            .incl(new Topping("Jalapeno",ToppingsFactory.get("Jalapeno").price,0)),
          Crust.get("Regular"),220),
    "Veggie Paradise" -> new Pizza("Veggie Paradise",
          new Empty()
            .incl(new Topping("Baby Corn",ToppingsFactory.get("Baby Corn").price,0))
            .incl(new Topping("Olives",ToppingsFactory.get("Olives").price,0))
            .incl(new Topping("Capsicum",ToppingsFactory.get("Capsicum").price,0))
            .incl(new Topping("Paprika",ToppingsFactory.get("Paprika").price,0)),
          Crust.get("Regular"),230),
    "Peppy Panner" -> new Pizza("Peppy Panner",
          new Empty()
            .incl(new Topping("Panner",ToppingsFactory.get("Panner").price,0))
            .incl(new Topping("Capsicum",ToppingsFactory.get("Capsicum").price,0))
            .incl(new Topping("Paprika",ToppingsFactory.get("Paprika").price,0)),
          Crust.get("Regular"),230),
    "Zesty Chicken" -> new Pizza("Zesty Chicken",
          new Empty()
            .incl(new Topping("Chicken Sausage",ToppingsFactory.get("Chicken Sausage").price,0))
            .incl(new Topping("Barbeque Chicken",ToppingsFactory.get("Barbeque Chicken").price,0))
            .incl(new Topping("Capsicum",ToppingsFactory.get("Capsicum").price,0))
            .incl(new Topping("Paprika",ToppingsFactory.get("Paprika").price,0)),
          Crust.get("Regular"),280),
    "Chicken Maxicana" -> new Pizza("Chicken Maxicana",
          new Empty()
            .incl(new Topping("Onion",ToppingsFactory.get("Onion").price,0))
            .incl(new Topping("Tomato",ToppingsFactory.get("Tomato").price,0))
            .incl(new Topping("Paprika",ToppingsFactory.get("Paprika").price,0))
            .incl(new Topping("Spicy Chicken",ToppingsFactory.get("Spicy Chicken").price,0)),
          Crust.get("Regular"),300)
  )

  /**
    * Get Pizza from map based on key
    *
    * @param name
    * @return Pizza
    */
  def get(name: String): Pizza = pizzas.get(name) match {
    case Some(pizza) => pizza
    case None => throw new Error("Specified Pizza is Not Available")
  }
}
