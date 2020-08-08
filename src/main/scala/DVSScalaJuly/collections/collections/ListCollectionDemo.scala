package DVSScalaJuly.collections.collections

object ListCollectionDemo extends App {
  /*
  Key Differences Between Array and Linked List
  1. An indexedSeq, LinearSeq are the data structure that contains a collection of similar type data elements.
  2. In the indexedSeq the elements belong to indexes, i.e., if you want to get into the fourth element you have to write the variable name with its index or location within the square bracket.
  3. In a LinearSeq though, you have to start from the head and work your way through until you get to the fourth element.
  4. Accessing an element in an indexedSeq is fast, while LinearSeq takes linear time, so it is quite a bit slower.
  5. Operations like insertion and deletion in indexedSeqs consume a lot of time. On the other hand, the performance of these operations in LinearSeqs is fast.
  6. Elements are stored consecutively in indexedSeqs whereas it is stored randomly in LinearSeqs.
  7. Inserting a new element in an indexedSeq of elements is expensive because a room has to be created for the new elements and to create room existing elements have to be shifted.
  8. The requirement of memory is less due to actual data being stored within the index in the indexedSeq. As against, there is a need for more memory in LinearSeqs due to storage of additional next and previous referencing elements.
   */


  val s = Seq(1,2,3)
  val lst: List[Int] = List(1,2,3,4,5,1,2,1)
  //val lst = Array(1,2,3,4,5,1,2,1)
  //val lst = Vector(1,2,3,4,5,1,2,1)
  val ls = List("scala","spark","kafka")
  val l1 = List.range(1,101)
  // :: cons operator
  val l2 = 10::2::7::9::15::Nil
  val l3 = List.fill(10)(5)
  val l4 = (1 to 10).toList
  val lc = List.range('a','g')
  /*
    list elements can be accessed using index
    List index starts from 0
    List stores duplicate elements
   */
  println(lst)
  println("No of elements of lst  : " + lst.length,lst.size)
  println("first element of lst is : "+ lst(0))
  println("second element of lst is : "+ lst(1))
  println("last element of lst is : "+ lst(4))

  println("first element of lst is : "+ lst.head)
  println("tail elements of lst is : "+ lst.tail)
  println("initial elements of lst is : "+ lst.init)
  println("last element of lst is : "+ lst.last)

  println("max element of lst is : "+lst.max)
  println("min element of lst is : "+lst.min)

  println("unique element or remove duplicates of lst : "+lst.distinct)
  println("drop 3 elements of lst: "+lst.drop(3))
  println("drop 3 elements of lst from right: "+lst.dropRight(3))
  println("original lst vales : "+lst)
  println("take first 2 elements of lst : "+lst.take(2))
  println("take first 2 elements of lst from right : "+lst.takeRight(2))
  println("take from 2nd element to 5th element of lst : "+lst.slice(2,5))

  println("sort lst elements : "+lst.sorted)
  println("sort lst elements in reverse : "+lst.sorted.reverse)
  println("reverse lst elements : "+lst.reverse)

  // List conversions
  println("convert list collection into Array : "+lst.toArray)
  println("convert list collection into Vector : "+lst.toVector)
  println("convert list collection into Set : "+lst.toSet)

  println("concat 2 list collections into one : ", lst++l1)
  println("concat 2 list collections into one : ", lst.union(ls))
  println("concat 2 list collections into one : ", lst:::l1)
  val lst1 = List(1,2,3)
  println("remove common elements from list : "+ lst.diff(lst1))
  println("common elements from list : "+ lst.intersect(lst1))

  println(lst)

  println("To check if list is empty : "+ lst isEmpty)
  println("zip two list objects : ", lst.zip(lst))
  println("add index numbers to list : "+ lst.zipWithIndex)

  println("check if 3 number is there in list : "+ lst.contains(3))
  println("check if 10 number is there in list : "+ lst.contains(10))
  println("convert list elements in string: "+ lst.mkString("-"))
  println("convert list elements in string: "+ lst.mkString("Numbers are ","-",""))

  //flatten :
  val nl = List(List(1,2,3),List(4,5,6))
  println("Before flatten : ",nl)
  println("flattened output : ",nl.flatten)
  //println("flattened output : ",nl.flatten.map(x => x*x))


  for(l <- lst) {
    println(l+10)
  }

  for(l <- lst) yield {
    l+10
  }

  // Higer order functions on List

  /*
    1) foreach
    def foreach(f:(A) => U)
    foreach will take one function argument
    foreach will not return any output
   */

  val ll = List(1,2,3,4,5)

  def func(x:Int) = println(x)

  println("printing each element :")
  ll.foreach(func)
  ll.foreach((x:Int) => println(x))

  //When we use anonymous functions with collections, argument type will be optional
  ll.foreach(x => println(x))
  ll.foreach(println)
  ll.foreach(elm => println(elm*elm))

  println("printing square of each elements :")
  ll.foreach((x:Int) => {
    val sqr = x*x
    println(sqr)
    //code to save all these elements into file
    //or write code to save all elements in DB
  })

  def myPrint(x:Int) = println(x)
  ll.foreach(myPrint)

  /*
    2) map function
    def map(f:(A) => B)
    1) map will take one function argument
    2) map function will always return same collection as output
    3) returned elements type can be same or different
    4) no of elements returned will be always same as original collection object
    5) map function is sililer to select clouse in sql
   */

  val mapOutput = ll.map((x:Int) => x*x)

  println("original elements are : ",ll)
  println("mapOutput is : ",mapOutput)
  val outputWithAnotherType = ll.map((x:Int) => (x*x*x).toLong)
  println(outputWithAnotherType)

  class MyInt(x:Int)
  println(ll.map(x => new MyInt(x)))

  ll.map(_*2)//not recomended for beginners
  ll.map(x => x*2)
  ll.map((x:Int) => x*2)

  /*
  3) filter or filterNot
  def filter(f:(A) => Boolean)
    1) filter will take one function argument
    2) filter function will always return same collection as output
    3) returned elements type will always remain same as original type
    4) no of elements returned will be 0 to n
    5) filter function is similar to where clouse in sql
   */

  val filteredLst = ll.filter((x:Int) => x>2)
  println("filter output : ",filteredLst)
  println("filter output : ",ll.filter(_>2))

  //applying multiple higher order function together

  ll.map((a:Int) => a*a).filter((y:Int) => y>10).foreach(println)

  val strLst = List("scala","spark","hive")

  strLst.filter(x => x.length>=5).map(x => x.toUpperCase()).foreach(println)
  println("filterNot output :")
  strLst.filterNot(x => x.length>=5).map(x => x.toUpperCase()).foreach(println)


  /*
  4) flatMap
  def flatMap(f:(A) => B)
    1) flatMap will take one function argument
    2) flatMap function will always return same collection as output
    3) returned elements type will be different as we are flatting nested collection object
    4) no of elements returned will either n or more
   */

  val flattenOutput = nl.flatMap(x => x.map(x => x*x))

  println("Nested list output after flatMap : ",flattenOutput)

  val nestedLst = List(List(List(1,2,3)),List(List(4,5,6)))
  val nestedOut = nestedLst.flatMap(first => first.flatMap(second => second.map(third => third*third)))
  println("output for 3 level nested List : ",nestedOut)

  /*
  5) sortBy
  def sortBy(f:(A) => B)
    1) sortBy will take one function argument
    2) sortBy function will always return same collection as output
    3) returned elements type will be same
    4) no of elements returned will same as well
    5) Only elements order will be changed
   */

  case class Emp(id:Int,name:String,sal:Double)
  val empLst = List(Emp(101,"Ramesh",15000),Emp(102,"Hari",10000),Emp(100,"Prasad",2500))

  println("sort employes based on salary : ", empLst.sortBy(emp => emp.sal))
  println("sort employes based on salary in desc : ", empLst.sortBy(emp => -emp.sal))
  println("sort employes based on name : ", empLst.sortBy(emp => emp.name))

  val empLst1 = List(Emp(101,"Ramesh",15000),Emp(102,"Hari",10000),Emp(100,"Prasad",2500))
  empLst1.sortBy(abc => abc.sal)
  println("sort employes based on name : ", empLst1.sortBy(emp => emp.sal))

  /*
  6) count
  def count(f:(A) => Boolean)
    1) count will take one function argument
    2) count function will always return Int/Long as output
   */

  val count = lst.count(x => x>3)
  println("no of elements greater than 3 are : " + count)

  /*
    7) groupBy
    def groupBy(f:K => List[A]) : Map[K,V]
      1) groupBy will take one function argument
      2) groupBy function will always return Map collection as output
      3) returned elements type will be always Key,Value pairs
      4) no of elements returned will can be same or less
   */

  val langLst = List("scala","python","Python","scala","java","scala","ruby")

  val grouppedLangs = langLst.groupBy(x => x.toUpperCase)
  println("=========groupped elements are ==========")
  grouppedLangs.foreach(println)
  println("=========count of each language ==========")
  grouppedLangs.map(x => s"${x._1} -> ${x._2.size}").foreach(println)


  // Higher order function which takes 2 argument function

  /*
    8) reduce
    def reduce(f:(A,I)) => A) : A
      1) reduce will take one function with two argument
          first one is being accumulator
          second one iterator
      2) reduce function will always return non collection type as output
   */

  val agglst = List(3, 2, 4, 5, 1, 6, 7, 10, 9, 8)
  //sum all elements
  val sumOut = agglst.reduce((acc:Int,itr:Int) => {
    println(acc,itr)
    acc+itr
  })

  println("Sum using reduce method : "+sumOut)
  val maxOut = agglst.reduce((acc,itr) => {println(acc,itr);if(acc>itr) acc else itr})
  println("Max using reduce method : "+maxOut)

//  /*
//    7) reduceLeft and reduceRight
//    def reduceLeft(f:(A,I)) => A) : A
//      * reduceLeft is same as reduce and it starts agg from left side
//      *reduceRight starts aggrigation from right side
//   */
//
//  agglst.reduceRight((itr,acc) => {println(itr,acc);if(acc>itr) acc else itr})
//
//  /*
//    8) fold
//    def fold(A)(f:(A,I)) => A) : A
//      1) fold will take two function with two argument
//          1) initial value for accumulator
//          2) function with two argument
//              i) first one is being accumulator
//              ii) second one iterator
//      2) fold function will always return non collection type as output
//   */
//
//  val foldOut = agglst.fold(100)((acc,itr) => acc+itr)
//
//  println("Sum using fold function : "+ foldOut)
//  val initial = 1000
//  val foldleftOut = agglst.foldLeft(initial)((acc,itr) => acc+itr)
//  val foldRightOut = agglst.foldRight(100)((acc,itr) => acc+itr)
//
//  agglst.foldRight(100)(_+_)
//  agglst.reduceLeft(_+_)


}
