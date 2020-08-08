package com.dvs.spark.implicits

import ImplicitDefs._

/*rule 1 - function should take one argument of type which you are trying to convert
rule 2 - only one implicit function for each type.
rule 3 - only one implicit parameter for each type.
*/

object ImplicitDefs {
  implicit def doubleToInt(x:Double):Int = x.toInt
}

object ImplicitFunctions {

  def add(x:Int)(implicit y:Int) = x+y
  def mul(x:Int)(implicit y:Int) = x*y

  def main(args: Array[String]): Unit = {
    implicit val abc = 10
    val a:Int = 12.15
    println(add(10)(20))
    println(add(100))
    println(mul(100))
  }
}
