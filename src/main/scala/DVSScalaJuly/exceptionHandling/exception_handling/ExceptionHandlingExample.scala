package DVSScalaJuly.exceptionHandling

import scala.io.Source

import java.io.{FileNotFoundException, IOException}

/*
 What is an Exception?

  1) An exception is an unwanted, unexpected event which disturbs the normal flow of the program.
  2) When an exception occurred then immediately program will terminate abnormally.
  3) There are situations that are not too dangerous and can be handled by the program.
  4) We need to handle those exceptions and define an alternative way to continue rest of the program normally.
  5) Defining an alternative way is nothing but exception handling.

 */

object ExceptionHandlingExample {
  import scala.io.Source


  def main(args: Array[String]) {

    try {
      println("inside try block")
      val file = scala.io.Source.fromFile("sample.txt").getLines()
      //file.foreach(println)
      println(file.toList)
      //val a = 100/0
      println("successfully read file")
    } catch {
      case fnf:FileNotFoundException => println(" File Not Found :", fnf.getMessage)
      case io:IOException => println("IOException raised")
      case ex:Exception => println("Exception raised",ex.printStackTrace())
    }
    finally {
      //it is optional block
      //If we want some part of our code to execute irrespective of how the expression terminates we can use a finally block.
      println("Exiting from finally...")
    }

    val a = 20
    val b = 30
    println("a+b is : " + (a+b))

  }
}