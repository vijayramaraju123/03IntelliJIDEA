package com.dvs.spark.oops

class Calculator {
  def add(a:Int,b:Int=0) = {
    println("a+b value is : "+ (a+b))
  }
  def add(a:Int,b:Int,c:Int) = {
    println("a+b+c value is : "+ (a+b+c))
  }
  def add(s1:String,s2:String) = {
    println("s1+s2 value is : "+ (s1+s2))
  }
}

class Addition1 extends Calculator {
  override def add(a:Int,b:Int) = {
    println("override add method from Addition class")
    println("A+B is : "+(a+b))
  }
}

class Addition2 extends Calculator {
}

object PolymorphismDemo {

  def main(args: Array[String]): Unit = {

    val c = new Calculator
    //Static Polymorphism \ Parametric \ Compile time Polymorphism \ Method Over Loading
//    c.add(10,5)
//    c.add("Scala ","Spark")
//    c.add(10,12,15)
  // Dynamic Polymorphism \ Sub Type \ Runtime Polymorphism \ Method Overriding
    val a1 = new Addition1
    a1.add(10,20)
    a1.add("10 ","20")
    val a2 = new Addition2
    a2.add(10,20)
    a2.add("10 ","20")

  }

}
