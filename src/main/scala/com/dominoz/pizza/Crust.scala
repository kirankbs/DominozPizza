package com.dominoz.pizza

/**
  * Created by kirankumar on 14-08-2016.
  */
class Crust(val name: String,val price: Int) {
  override def toString =
    "Crust: "+ name + " ["+price+"]"
}

object Crust{
 private val crusts = Map(
    "Regular" -> new Crust("Regular",0),
    "Pan Pizza" -> new Crust("Pan Pizza",100),
    "Thin Crust" -> new Crust("Thin Crust",150),
    "Cheese Burst" -> new Crust("Cheese Burst",200))

  /**
    * Get Crust from map based on key
    * @param name
    * @return Crus
    */
  def get(name: String): Crust = crusts.get(name) match {
    case Some(x) => x
    case None => throw new Error("Specified Crust is Available")
  }
}


