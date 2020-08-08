package training.sparkCoreTraining

import org.apache.spark.{SparkConf, SparkContext}

object SparkCoreJoins {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(getClass.getName)
    val sc = new SparkContext(conf)

   sc.setLogLevel("ERROR")

    // Loading sales file
    val salesRDD=sc.textFile("C:\\Users\\Incredible\\Documents\\run\\sales.csv")
  // salesRDD.foreach(println)

    // shema evaluation
val schemaRdd=salesRDD.map(u => {
  val Arr = u.split(",")
  (Arr(1),Arr(3).toDouble)})
  //  schemaRdd.foreach(println)


    println(schemaRdd.getClass())

    val totalSalesRDD= schemaRdd.reduceByKey(_+_).sortByKey().cache()
    totalSalesRDD.foreach(println)

    val customersRDD=sc.textFile("C:\\Users\\Incredible\\Documents\\run\\customers.csv")
 //   customersRDD.foreach(println)

val schemaRddCust=customersRDD.map ( u => {
  val k = u.split(",")
  (k(0),k(1))}).cache()
    schemaRddCust.foreach(println)

    println("join Operation")
    val joinRdd=totalSalesRDD.join(schemaRddCust)
    joinRdd.foreach(println)
    joinRdd.map( u => (u._2._2,u._2._1)).foreach(println)

    println("left outer join")
    val joinRdd1=totalSalesRDD.leftOuterJoin(schemaRddCust).foreach(println)

    println("right outer join")
    val joinRdd2=totalSalesRDD.rightOuterJoin(schemaRddCust).foreach(println)

    println("full outer join")
    val joinRdd3=totalSalesRDD.fullOuterJoin(schemaRddCust).foreach(println)

    Thread.sleep(500000)
  }
}