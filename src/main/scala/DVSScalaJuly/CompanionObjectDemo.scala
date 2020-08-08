package com.dvs.spark.oops

class Employee private (val role:String="") {
  private val location = "Bangalore"
  def work() = println(s"Works as $role in ${Employee.org}")
}

object Employee {
  private val org = "DVS"
  val emp = Employee()
  def info() = println(s"Employee location ${emp.location}")
  def apply(role: String=""): Employee = new Employee(role)
  def info(abc:Employee) = println(s"${abc.location}, ${abc.role}")
}

object CompanionObjectDemo {
  def main(args: Array[String]): Unit = {
    val e = Employee("Trainer")
    e.work
    Employee.info
    val e1 = Employee("Trainer")
    e1.work()
  }
}
