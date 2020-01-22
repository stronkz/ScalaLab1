import Models.TransportType.TransportType
import Models.Voucher

class VoucherController {
  private val _storage = VoucherStorage
  def getAllVouchers = {
    _storage.getAll
  }

  def boolVoucher(index: Int): Boolean ={
    val allVouchers = this._storage.getAll
    if(allVouchers.length < index - 1) {
      return false
    }

    val selectedVoucher = allVouchers(index)
    selectedVoucher.bookVoucher

    this._storage.update(selectedVoucher)
  }

  def sortByPrice(acs: Boolean = false) = {
      this._storage.getAll.sortBy(s => s.price)( if(acs) Ordering.Int.reverse else Ordering.Int)
  }

  def filterByBooked() = {
    this.filterVouchers(this._storage.getAll, v => v.isBooked == true)
  }

  def filterByMeals(minAmount: Short) = {
    this.filterVouchers(this._storage.getAll, v => v.mealsAmount >= minAmount)
  }

  def filterByDaysAmount(minAmount: Short) = {
    this.filterVouchers(this._storage.getAll, v => v.daysAmount >= minAmount)
  }

  def filterByDaysAndMealsAmount(daysAmount: Short, mealsAmount: Short) = {
    this.filterVouchers(this._storage.getAll, v => v.daysAmount >= daysAmount && v.mealsAmount >= mealsAmount)
  }

  def filterByTransport(transport: TransportType) = {
    this.filterVouchers(this._storage.getAll, v => v.transport >= transport)
  }

  private def filterVouchers(vouchers: List[Voucher], comparison: Voucher => Boolean): List[Voucher] = {
    vouchers.filter(comparison)
  }
}
