package DVSScalaJuly.collections.collections


import scala.collection.mutable

object MutableSeqDemo extends App {

  //Mutable Indexed Seq Family

  val ab = mutable.ArrayBuffer(1,2,3,4,5)

  println("Original Array Buffer Values : ",ab)
  // adding single values
  ab+=6
  ab+=7
  println("Array Buffer Values are : ",ab)
  // adding multiple values
  ab+=(8,9,10)
  println("Array Buffer Values are : ",ab)
  // Adding collection object
  ab++=Array(11,12,13,14,15)
  println("Array Buffer Values are : ",ab)

  ab.append(16,17,18)

  //Removing elements in Array Buffer

  ab-=1
  println("Removing single element : ",ab)
  ab-=(4,5,6)
  println("Removing single element : ",ab)
  ab--=List(9,12,14)
  println("Removing single element : ",ab)
  ab.remove(5,3)

  //Modifiying array buffer elements
  ab(2)=22
  println("Updated value on 3rd element : ",ab)

  // insert elemnts at specific position
  ab.insert(2,101,102,103)
  println("Inserting elements at 2nd index : ",ab)

  ab.foreach(println)
  println("Reduce output on Array Buffer : ",ab.fold(100)((x,y) => x+y))


  /*
    Working with mutable LinearSeq
    1) preferred collection is ListBuffer
   */

  val lb = mutable.ListBuffer(1,2,3,4,5)

  println("Original ListBuffer Values : ",lb)
  // adding single values
  lb+=6
  lb+=7
  println("ListBuffer Values are : ",lb)
  // adding multiple values
  lb+=(8,9,10)
  println("ListBuffer Values are : ",lb)
  // Adding collection object
  lb++=Array(11,12,13,14,15)
  println("ListBuffer Values are : ",lb)

  lb.append(16,17,18)

  //Removing elements in ListBuffer

  lb-=1
  println("Removing single element : ",lb)
  lb-=(4,5,6)
  println("Removing single element : ",lb)
  lb--=List(9,12,14)
  println("Removing single element : ",lb)
  lb.remove(5,3)

  lb.foreach(println)
  println("Reduce output on ListBuffer : ",lb.fold(100)((x,y) => x+y))

  //Modifiying array buffer elements
  lb(2)=22
  println("Updated value on 3rd element : ",lb)

  // insert elemnts at specific position
  lb.insert(2,101,102,103)
  println("Inserting elements at 2nd index : ",lb)


}
