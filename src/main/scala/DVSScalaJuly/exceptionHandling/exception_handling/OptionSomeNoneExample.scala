package DVSScalaJuly.exceptionHandling


object OptionSomeNoneExample extends App {
  /*
    Scala Option[ T ] is a container for zero or one element of a given type.
    An Option[T] can be either Some[T] or None object, which represents a missing value.
   */

  def stringToInt(in: String): Option[Int] = {
    try {
      //val num = Integer.parseInt(in.trim)
      val num = in.toInt
      Some(num)
    } catch {
      case e: NumberFormatException => {
        println("invalid number : ")
        None
      }
      case e1: NullPointerException => None
      case ex: Exception => None
    }
//    if(!in.isEmpty || in.matches("[0-9]"))  Some(Integer.parseInt(in.trim))
//    else None
  }

  val output = stringToInt("100")

  println(output)

  output match {
    case Some(x) => println("output is : "+ x)
    case None => println("its none, some exception happened")
  }

  val output1 = stringToInt("100a")

  println("extracting output using getorElse :  "+ output.getOrElse(0))
  println("extracting output using getorElse :  "+ output1.getOrElse(10))

  println(stringToInt("100 0").getOrElse("some invalid data"))

//  val out1 = stringToInt("11")
//  println(out1.getOrElse("bad input"))
//  println(stringToInt("1 1").getOrElse("bad input"))

}
