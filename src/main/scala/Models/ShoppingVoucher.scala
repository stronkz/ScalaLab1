package Models

import Models.TransportType.TransportType

class ShoppingVoucher(price: Int, daysAmount: Short, mealsAmount: Short, transport: TransportType, hoursForShopping: Short, shops: List[String] )
  extends Voucher("Shopping", price, daysAmount, mealsAmount, transport ) {

  override def render: String = {
    s"${super.render}Shopping hours: ${hoursForShopping}; Visited shops: ${shops.mkString(", ")};"
  }

  override def copy: ShoppingVoucher = {
    val copy = new ShoppingVoucher(this.price, this.daysAmount, this.mealsAmount, this.transport, this.hoursForShopping, this.shops)
    if(this.isBooked) {
      copy.bookVoucher
    }

    copy
  }
}