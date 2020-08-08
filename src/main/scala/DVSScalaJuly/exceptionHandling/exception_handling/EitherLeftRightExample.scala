package DVSScalaJuly.exceptionHandling


/*
  A common use of Either is as an alternative to Option for dealing with possible missing values.
  In this usage, scala.None is replaced with a Left which can contain useful information.
  Right takes the place of Some. Convention dictates that Left is used for failure and Right is used for success.
 */

object EitherLeftRightExample extends App {

  /**
    * A simple method to demonstrate how to declare that a method returns an Either,
    * and code that returns a Left or Right.
    */

  def divideXByY(x: Double, y: Int): Either[String, Double] = {
    if (y == 0) Left("y value cannot be zero")
    else {
      val result = x/y
      Right(result)
    }
  }

  // a few different ways to use Either, Left, and Right
  println(divideXByY(11.1, 0)) // we must get Left object
  println(divideXByY(12.5, 2)) // we must get Right object

  divideXByY(10.1,5) match {
    case Left(x) => println("failure based logic" + x)
    case Right(x) => println("some success logic : " + x)
  }

  divideXByY(10.1,0) match {
    case Left(x) => println("failure based logic : " + x)
    case Right(x) => println("some success logic : " + x)
  }

  val output  = divideXByY(10.1,0) match {
    case Left(x) => x
    case Right(x) => x
  }

  println("return value is : "+output)

  val obj = divideXByY(12.5, 0)

  println(obj.isLeft)
  println(obj.isRight)

  if(obj.isLeft) println("left object: "+ obj.left.getOrElse(0) )
  else println("right object and division value is: "+obj.right.get)

  println("Output for 10,0 is : "+divideXByY(10, 0).right.getOrElse(0))
  println("Output for 10,2 is : "+divideXByY(10, 2).right.getOrElse(0))

  def divideXByY1(x: Double, y: Int): Either[ArithmeticException, Double] = {
    if (y == 0) {
      val ex = new ArithmeticException("y value cannot be zero")
      Left(ex)
    }
    //if (y == 0) Left("y value cannot be zero")
    //if(y == 0) Left(0)
    else Right((x / y))
  }

  println("divideXByY1 Output for 10,0 is : "+divideXByY1(10, 0).left.getOrElse(0))
  println("divideXByY1 Output for 10,2 is : "+divideXByY1(10, 2).right.getOrElse(0))

}
