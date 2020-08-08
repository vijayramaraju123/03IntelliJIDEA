package com.dvs.spark.oops

abstract class MobilePhone(model:String) {
  val os:String
  def make_call()
  def message()
  def calender() = println("Calender feature from mobile")
}

class Iphone(model:String) extends MobilePhone(model) {
  val os = "iOS"
  def make_call() = println("make call from Iphone")
  def message() = println("sending message from iPhone")
  def faceTime() = println("Calling using face time")
  override def calender(): Unit = println("my iphone fancy calender")
}

abstract class SamsungPhone(model:String) extends MobilePhone(model) {
  def make_call() = println("make call from Samsung")
  def message() = println("sending message from Samsung")
}

class GalaxyPhone(model:String) extends SamsungPhone(model) {
  val os = "Android"
}

object AbstractClassDemo extends App{
  val i10 = new Iphone("iPhone10")
  i10.make_call()
  i10.message()
  i10.calender()

  val s3 = new GalaxyPhone("Galaxy S3")
  s3.calender()
}
