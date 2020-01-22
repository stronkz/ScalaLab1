import Models.{CruiseVoucher, ExcursionVoucher, ShoppingVoucher, TransportType}

import scala.io.StdIn


object Main {
  def main(args: Array[String]): Unit = {

  var initialState = List(
    new CruiseVoucher(1000, daysAmount = 133, isBusinessClass = true, mealsAmount = 4, maxPersonsAmount = 2),
    new CruiseVoucher(500, daysAmount = 12, isBusinessClass = false, mealsAmount = 2, maxPersonsAmount = 2),
    new CruiseVoucher(1440, daysAmount = 53, isBusinessClass = true, mealsAmount = 4, maxPersonsAmount = 5),
    new CruiseVoucher(13330, daysAmount = 1333, isBusinessClass = true, mealsAmount = 1, maxPersonsAmount = 3),
    new ExcursionVoucher(1233, daysAmount = 321, transport = TransportType.Plane, mealsAmount = 4, cities = List("Minsk", "Grodno")),
    new ShoppingVoucher(321, daysAmount = 44, transport = TransportType.Car, mealsAmount = 2, shops = List("Adidas", "Nike", "Evroopt"), hoursForShopping = 4)
  )

    VoucherStorage.addMany(initialState)
    var vc = new VoucherController


    renderStep0Menu
    var menuPoint = 1
    var currentStep = 0
    while ({menuPoint = StdIn.readShort(); menuPoint != 111 }) {
      currentStep match {
        case 0 => {
          menuPoint match {
            case 1 => {
              println("\n\n------------------")
              vc.getAllVouchers.foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 2 => {
              println("\n\n------------------")
              vc.filterByBooked().foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 3 => {
              currentStep = 3
            }
            case 4 => {
              currentStep = 4
            }
            case 5 => {
              println("Введите порядковый номер путёвки")
              val num = StdIn.readShort()
              if(vc.boolVoucher(num)) println("успешно забронировано") else println("произошла ошибка при бронировании")
            }
            case _  => println("неверная цифра")
          }
        }
        case 3 => {
          menuPoint match {
            case 1 => {
              println("\n\n------------------")
              vc.sortByPrice().foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 2 => {
              println("\n\n------------------")
              vc.sortByPrice(true).foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 0 => {
              currentStep = 0
            }
            case _  => println("неверная цифра")
          }
        }
        case 4 => {
          menuPoint match {
            case 1 => {
              println("Тип транспорта (0 - самолёт, 1 - поезд, 2 - машина, 3 - автобус, 4 - паром)")
              val transport = StdIn.readShort()
              println("\n\n------------------")
              vc.filterByTransport(TransportType(transport)).foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 2 => {
              println("Введите минимальное число приёмов пищи")
              val mealsAmount = StdIn.readShort()
              println("\n\n------------------")
              vc.filterByMeals(mealsAmount).foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 3 => {
              println("Введите минимальное кол-во дней")
              val days = StdIn.readShort()
              println("\n\n------------------")
              vc.filterByDaysAmount(days).foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 4 => {
              println("Введите минимальное кол-во дней и минимальное кол-во приемов пищи (через запятую)")
              val data = StdIn.readLine()
              val splitedData: Array[String] = data.split(',')
              println("\n\n------------------")
              vc.filterByDaysAndMealsAmount(splitedData(0).toShort, splitedData(1).toShort).foreach(v => println(v.render))
              println("------------------\n\n")
            }
            case 0 => {
              currentStep = 0
            }
            case _  => println("неверная цифра")
          }
        }
      }

      currentStep match {
        case 0 => renderStep0Menu
        case 3 => renderStep3_1Menu
        case 4 => renderStep4_1Menu
        case _  => println("неверная цифра")
      }
    }
  }

  def renderStep0Menu: Unit ={
    println("1 - для вывода всех путевок")
    println("2 - для вывода выбранных путевок")
    println("3 - для сортировки по цене")
    println("4 - для фильтра")
    println("5 - для бронирования путевки")
    println("111 - для выхода")
  }

  def renderStep3_1Menu: Unit ={
    println("1 - дешевле -> дороже")
    println("2 - дороже -> дешевле")
    println("0 - назад")
  }

  def renderStep4_1Menu: Unit ={
    println("1 - по транспорту")
    println("2 - по кол-ву приёмов пищи")
    println("3 - по кол-ву дней")
    println("4 - по кол-ву дней и кол-ву приемов пищи")
    println("0 - назад")
  }

  def renderStep4_1_1Menu: Unit ={

    println("0 - назад")
  }

  def renderStep4_1_2Menu: Unit ={
    println("Введите минимальное число приёмов пищи")
    println("0 - назад")
  }

  def renderStep4_1_3Menu: Unit ={

    println("0 - назад")
  }

  def renderStep4_1_4Menu: Unit ={

    println("0 - назад")
  }
}