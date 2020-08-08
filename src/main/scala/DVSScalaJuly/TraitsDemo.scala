package com.dvs.spark.oops.traits


trait MobilePhone {
  val os:String
  def make_call()
  def message()
  def calender() = println("Basic calender feature from mobile")
}

trait SmartPhone {
  def playYoutube() = println("playing youtube video")
}

class Iphone(model:String) extends SmartPhone with MobilePhone  {
  val os = "iOS"
  def make_call() = println("make call from Iphone")
  def message() = println("sending message from iPhone")
  def faceTime() = println("Calling using face time")
}

abstract class SamsungPhone(model:String) extends MobilePhone {
  def make_call() = println("make call from Samsung")
  def message() = println("sending message from Samsung")
}

class GalaxyPhone(model:String) extends SamsungPhone(model) with SmartPhone {
  val os = "Android"
}

class SamsungFeatuePhone(model:String) extends SamsungPhone(model){
  val os = "basic os"
}

object TraitsDemo {
  def main(args: Array[String]): Unit = {
    val i10 = new Iphone("iPhone10")
    i10.make_call()
    i10.message()
    i10.calender()

    val s10 = new GalaxyPhone("s10")
    s10.playYoutube()
    s10.calender()

    val fp = new SamsungFeatuePhone("abc")
    fp.make_call()
  }
}
