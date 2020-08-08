package com.dvs.spark.implicits

import com.dvs.spark.oops.Employee
import Implicits._

object Implicits1 {

  implicit class StringOps(s:String) {
    def sayHello = println(s"Hello $s")
  }

  implicit class IntExtras(x:Int) {
    def myDouble() = println(s"${x.toDouble}")
  }

  implicit class EmployeeAdditionalMembers(e:Employee) {
    def display() = println(e.role)
  }

}

/*rule1 - implicit classes should be written inside class/object
rule 2 - implicit classes should take exactly one constructor argument of the type which we are trying expand.
rule 3 - there should only one implicit class for each type in scope
*/

object ImplicitsClassDemo1 {
  def main(args: Array[String]): Unit = {

    val s = "scala"
    println(s.toUpperCase())
    println(s.toLowerCase)
    s.sayHello

    val a = "Prasad"
    a.sayHello

    val emp = Employee("Trainer")
    emp.display()
    emp.work()

  }
}
