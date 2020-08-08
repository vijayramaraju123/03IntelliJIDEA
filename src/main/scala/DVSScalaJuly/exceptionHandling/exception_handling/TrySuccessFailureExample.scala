package DVSScalaJuly.exceptionHandling


import scala.util.{Failure, Success, Try}

/*
  The Try type represents a computation that may either result in an exception, or return a successfully computed value.
  It's similar to, but semantically different from the scala.util.Either type.

  Instances of Try[T], are either an instance of Success[T] or Failure[T].
 */
object TrySuccessFailureExample extends App {

  def readTextFile(filename: String): Try[List[String]] = {
    Try(scala.io.Source.fromFile(filename).getLines.toList)
  }

  println(readTextFile("sample.txt"))
  println(readTextFile("sample1.txt"))

  readTextFile("sample.txt") match {
    case Success(lst) => lst.foreach(println)
    case Failure(flr) => println("Failure :"+flr.getMessage)
  }

  readTextFile("sample1.txt") match {
    case Success(lst) => lst.foreach(println)
    case Failure(flr) => println("Failure :"+flr.getMessage)
  }

  val output = readTextFile("sample.txt")

  println(output.isSuccess)
  println(output.isFailure)

  if(output.isFailure) println("failure object: "+ output)
  else println("Success object value is: "+output.get)

  def stringToInt(in: String): Try[Int] = {
    Try {
      val num = Integer.parseInt(in.trim)
      num
    }
  }

  println(stringToInt("100"))
  println(stringToInt("100a"))

}
