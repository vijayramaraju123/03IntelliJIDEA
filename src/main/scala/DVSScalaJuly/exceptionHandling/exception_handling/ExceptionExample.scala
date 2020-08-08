package DVSScalaJuly.exceptionHandling
import scala.io.Source

object ExceptionExample {

  def main(args: Array[String]): Unit = {
    val file = scala.io.Source.fromFile("sample.txt").getLines()
    file.foreach(println)
    val a = 20
    val b = 30
    println("a+b is : " + (a+b))

    val fileOut = IOUtils.readFile("sample1.txt")
    println(fileOut)

  }
}

object IOUtils {
  def readFile(path: String) = scala.io.Source.fromFile("sample1.txt").getLines().toList
}
