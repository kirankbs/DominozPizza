package com.dominoz.order

/**
  * Created by kirankumar on 15-08-2016.
  */
trait Tax {
  def applyTax(orderPrice: Int): Int
}

private class ServiceTax(rate: Int) extends Tax{
  override def applyTax(orderPrice: Int): Int = (orderPrice *rate)/100
}
private class Vat(rate: Int) extends  Tax{
  override def applyTax(orderPrice: Int): Int = (orderPrice * rate)/100
}

object Tax{
  def getServiceTax(rate: Int,orderPrice: Int): Int = new ServiceTax(rate).applyTax(orderPrice)
  def getVat(rate: Int,orderPrice: Int): Int = new Vat(rate).applyTax(orderPrice)
}
