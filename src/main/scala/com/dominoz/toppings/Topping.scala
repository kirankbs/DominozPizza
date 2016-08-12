package com.dominoz.toppings

/**
  * Created by kirankumarbs on 8/12/2016.
  */
class Topping(val name: String, val price: Int) {
  override def toString: String =
      "Topping: "+ name + " [" + price + "]"
}

trait ToppingSet{
  type topFilter = String => Boolean

  def price(p: Topping => Boolean): Int = priceAcc(p,0)
  def priceAcc(p: Topping => Boolean, acc: Int):Int

  def totalToppingsPrice(): Int = totalToppingsPriceAcc(0)
  def totalToppingsPriceAcc(acc: Int):Int

  def incl(topping: Topping): ToppingSet
  def remove(p: Topping => Boolean): ToppingSet = removeAcc(p,new Empty)
  def removeAcc(p: Topping => Boolean, acc: ToppingSet): ToppingSet
  def contains(topping: Topping): Boolean
}

class Empty extends ToppingSet{

  override def priceAcc(p: (Topping) => Boolean, acc: Int): Int = acc

  override def totalToppingsPriceAcc(acc: Int): Int = acc

  override def incl(topping: Topping): ToppingSet = new NonEmpty(topping,new Empty,new Empty)

  override def contains(topping: Topping): Boolean = false

  override def removeAcc(p: (Topping) => Boolean, acc: ToppingSet): ToppingSet = acc
}

class NonEmpty(top: Topping, left: ToppingSet, right: ToppingSet) extends ToppingSet{

    def priceAcc(p: Topping => Boolean, acc: Int): Int =
      right.priceAcc(p, left.priceAcc(p, if (p(top)) return acc+top.price else acc))

    def totalToppingsPriceAcc(acc: Int): Int =
     right.totalToppingsPriceAcc(left.totalToppingsPriceAcc(acc+top.price))

  override def incl(topping: Topping): ToppingSet =
    if(topping.name < top.name) new NonEmpty(top,left.incl(topping),right)
    else if(topping.name > top.name) new NonEmpty(top,left,right.incl(topping))
    else this

  override def contains(topping: Topping): Boolean =
    if(topping.name < top.name) left.contains(topping)
    else if(topping.name > top.name) right.contains(topping)
    else true

  override def removeAcc(p: Topping => Boolean, acc: ToppingSet): ToppingSet =
    right.removeAcc(p,left.removeAcc(p,if(p(top)) acc else  acc.incl(top)))
}

object ToppingSet{

}
