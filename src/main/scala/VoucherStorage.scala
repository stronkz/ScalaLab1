import Models.Voucher

import scala.collection.mutable.ListBuffer

object VoucherStorage {
  private val _vouchers = new ListBuffer[Voucher]()
  private var counter = 1

  def amount = {
    _vouchers.length
  }

  def addMany(vouchers: List[Voucher]) = {
    vouchers.foreach(v => this.add(v))
  }

  def add(voucher: Voucher): Boolean = {
    if(voucher.id == 0) {
      voucher.id = counter
      counter += 1
    }
    else if (_vouchers.exists(v => v.id == voucher.id)) {
      return  false
    }

    this._vouchers += voucher.copy
    true
  }

  def removeById(id: Int): Boolean = {
    if(! _vouchers.exists(v => v.id == id)) return false

    _vouchers.remove(_vouchers.indexWhere(v => v.id == id), 1)
    true
  }

  def update(voucher: Voucher): Boolean = {
    if(! _vouchers.exists(v => v.id == voucher.id)) return false

    this.removeById(voucher.id)
    this.add(voucher)
  }

  def getAll = {
    _vouchers.map(v => v.copy).toList
  }
}