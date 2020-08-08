package com.dvs.spark.oops

object Student {
  def apply() = println("Apply method from Student object")
  def info() = println("Infor method from Student Object")
  def apply(arg1:Int) = println("1 arg apply method from Student Object")
}

class EeeStudent {
  def apply() = println("apply method from EeeStudent class")
  def apply(arg1:Int) = println("1 arg apply method from EeeStudent class")
}

object ApplyMethodDemo {
  def main(args: Array[String]): Unit = {
    Student.info()
    Student.apply()
    Student()
    Student(10)

    val ee = new EeeStudent
    ee.apply()
    ee()
    ee(10)
  }
}
