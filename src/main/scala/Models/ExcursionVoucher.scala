package Models

import Models.TransportType.TransportType

class ExcursionVoucher(price: Int, daysAmount: Short, mealsAmount: Short, transport: TransportType, cities: List[String] )
  extends Voucher("Excursion", price, daysAmount, mealsAmount, transport ) {

  override def render: String = {
    s"${super.render}Visited cities: ${cities.mkString(", ")}"
  }

  override def copy: ExcursionVoucher = {
    val copy = new ExcursionVoucher(this.price, this.daysAmount, this.mealsAmount, this.transport, this.cities)
    if(this.isBooked) {
      copy.bookVoucher
    }

    copy
  }
}