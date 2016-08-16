package com.dominoz.pizza

/**
  * Created by kirankumarbs on 8/12/2016.
  */
class Topping(val name: String, val price: Int, val quantity: Int = 1) {
  override def toString: String =
      "Topping: "+ name + " [" + price + "]"
}

trait ToppingSet{
  type toppingsFilter = Topping => Boolean

  def price(p: toppingsFilter): Int = priceAcc(p,0)
  def priceAcc(p: toppingsFilter, acc: Int):Int

  def totalToppingsPrice(): Int = totalToppingsPriceAcc(0)
  def totalToppingsPriceAcc(acc: Int):Int

  def incl(topping: Topping): ToppingSet
  def contains(topping: Topping): Boolean

  def remove(p: toppingsFilter): ToppingSet = removeAcc(p,new Empty)
  def removeAcc(p: toppingsFilter, acc: ToppingSet): ToppingSet
}

class Empty extends ToppingSet{

  override def priceAcc(p: toppingsFilter, acc: Int): Int = acc

  override def totalToppingsPriceAcc(acc: Int): Int = acc

  override def incl(topping: Topping): ToppingSet = new NonEmpty(topping,new Empty,new Empty)

  override def contains(topping: Topping): Boolean = false

  override def removeAcc(p: toppingsFilter, acc: ToppingSet): ToppingSet = acc

}

class NonEmpty(top: Topping, left: ToppingSet, right: ToppingSet) extends ToppingSet{

    def priceAcc(p: toppingsFilter, acc: Int): Int =
      right.priceAcc(p, left.priceAcc(p, if (p(top)) return acc+top.price else acc))

    def totalToppingsPriceAcc(acc: Int): Int =
     right.totalToppingsPriceAcc(left.totalToppingsPriceAcc(acc+(top.price*top.quantity)))

  override def incl(topping: Topping): ToppingSet =
    if(topping.name < top.name) new NonEmpty(top,left.incl(topping),right)
    else if(topping.name > top.name) new NonEmpty(top,left,right.incl(topping))
    else if(topping.name.equalsIgnoreCase(top.name))
      new NonEmpty(new Topping(top.name,top.price,topping.quantity),left,right)
    else this

  override def contains(topping: Topping): Boolean =
    if(topping.name < top.name) left.contains(topping)
    else if(topping.name > top.name) right.contains(topping)
    else true

  override def removeAcc(p: toppingsFilter, acc: ToppingSet): ToppingSet =
    right.removeAcc(p,left.removeAcc(p,if(p(top)) acc else  acc.incl(top)))


}

object ToppingsFactory{

  private val toppings =
    Map("Tomato" -> new Topping("Tomato",10),
    "Baby Corn" -> new Topping("Baby Corn",10),
    "Pineapple" -> new Topping("Pineapple",20),
    "Jalapeno" -> new Topping("Jalapeno",15),
    "Chicken Salami" -> new Topping("Chicken Salami",95),
    "Olives" -> new Topping("Olives",15),
    "Capsicum" -> new Topping("Capsicum",10),
    "Paprika" -> new Topping("Paprika",30),
    "Panner" -> new Topping("Panner",80),
    "Chicken Sausage"-> new Topping("Chicken Sausage",110),
    "Barbeque Chicken" -> new Topping("Barbeque Chicken",100),
    "Spicy Chicken" -> new Topping("Spicy Chicken",90),
    "Onion" -> new Topping("Onion",10),
    "Cheese" -> new Topping("Cheese",20),
    "Smoked Chicken" -> new Topping("Smoked Chicken",150))

  /**
    * Get Topping from map based on key
    * @param name
    * @return Topping
    */
  def get(name: String): Topping = toppings.get(name) match {
    case Some(x) => x
    case None => throw new Error(s"$name Topping Available")
  }
}
