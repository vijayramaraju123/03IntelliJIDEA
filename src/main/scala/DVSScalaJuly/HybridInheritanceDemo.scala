package com.dvs.spark.oops.traits

trait Phone {
  def calender() //= println("Some calender")
}

trait BasicPhone extends Phone {
  val os:String
  def make_call()
  def message()
  override def calender() = println("Basic calender feature from mobile")
}

trait SmartMobilePhone extends Phone {
  def playYoutube() = println("playing youtube video")
  override def calender() = println("Smart calender feature from mobile")
}

class ApplePhone(model:String) extends BasicPhone with SmartMobilePhone {
  val os = "iOS"
  def make_call() = println("make call from Iphone")
  def message() = println("sending message from iPhone")
  def faceTime() = println("Calling using face time")
}

object HybridInheritanceDemo {
  def main(args: Array[String]): Unit = {
    val i10 = new ApplePhone("iPhone10")
    i10.make_call()
    i10.message()
    i10.calender()
  }
}
