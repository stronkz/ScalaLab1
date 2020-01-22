package Models

object TransportType extends Enumeration {
  type TransportType = Value
  val Plane, Train, Car, Bus, Sheep = Value
}
import TransportType._

abstract class Voucher(voucherType: String, val price: Int, val daysAmount: Short, val mealsAmount: Short, val transportType: TransportType ) {
  private var _isBooked: Boolean = false
  private var _id: Int = 0

  def id = _id
  def id_= (newValue: Int): Unit = {
    if(_id != 0) {
      println("Id is set already")
    } else if(newValue <= 0){
      println("out of range value")
    } else {
      _id = newValue
    }
  }

  def isBooked = _isBooked

  def transport: TransportType = transportType

  def render: String = {
    s"Is booked by you: ${_isBooked}; Type: ${voucherType}; Price: ${price}$$; Days in trip: ${daysAmount}; Amount of meals a day: ${mealsAmount}; Transport by: ${transportType.toString}; "
  }

  def copy: Voucher

  def bookVoucher = {
    if(!_isBooked) _isBooked = true
  }

  def unbookVoucher = {
    if(_isBooked) _isBooked = false
  }
}
