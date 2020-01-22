package Models

class CruiseVoucher(price: Int, daysAmount: Short, mealsAmount: Short, isBusinessClass: Boolean, maxPersonsAmount: Short )
  extends Voucher("Cruise", price, daysAmount, mealsAmount, TransportType.Sheep ) {

  override def render: String = {
    s"${super.render}Buisiness class: ${ if(isBusinessClass) "yes" else "no"}; Max amount of persons in room: ${maxPersonsAmount}"
  }

  override def copy: CruiseVoucher = {
    val copy = new CruiseVoucher(this.price, this.daysAmount, this.mealsAmount, isBusinessClass, this.maxPersonsAmount)
    if(this.isBooked) {
      copy.bookVoucher
    }

    copy
  }
}