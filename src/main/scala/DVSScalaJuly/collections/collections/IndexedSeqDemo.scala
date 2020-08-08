package DVSScalaJuly.collections.collections

object IndexedSeqDemo extends App{

  //Array
  val arr = Array(1,2,3,4,5,6,7)
  val arr1 = (1 to 10).toArray

  println("github checking")
  println("Search array elements : ",arr.apply(3),arr(5))
  println(arr.getClass)

  /*
    1) Arrays allows update on its elements
    2) Add, Delete operations are not allowed
   */

  //update array element
  println("original array : " + arr.toList)
  println("value in index 3 is : " + arr(3))
  arr(3)=33
  println("value in index 3 is : " + arr(3))
  arr(3)=44
  println("value in index 3 is : " + arr(3))
  println("modified array : " + arr.toList)

  println(arr.min,arr.sum,arr.length)

  arr.foreach(println)
  println("=========map method on Array=============")
  arr.map(x => x*x).foreach(println)
  println()
  println("reduce output is : "+arr.reduce((x,y) => x+y))

  // String as collection

  println("========String as collection============")
  val str = "scala is easy"

  str.foreach(print)
  println()
  str.map(x => (x-32).toChar).foreach(print)
  println()
  str.sortBy(x => -x).foreach(println)

  // Vector Types

  println("=======Vector Collection============")
  val indSeq = IndexedSeq(1,2,3,4,5)
  val vctr = Vector(1,2,3,4,5)
  println("indSeq object created is : ", indSeq)
  println("vector obj creation using Vector : ", vctr)

  println("value in index 3 is : " + vctr(3))

  println(vctr.min,vctr.sum,vctr.length)

  vctr.foreach(print)
  vctr.map(x => x*x).foreach(print)
  println()
  println("reduce output is : "+vctr.reduce((x,y) => x+y))

  // Range
  // used in for loops
  // all index based operations works here as well

  val range = Range(1,10,2)
  val range1 = (1 to 10)
  val range2 = ('a' to 'z')

  println("range values are : ", range)
  println("range1 values are : ", range1)
  println("range2 values are : ", range2)

  range.map(x => x*x).foreach(println)

}
