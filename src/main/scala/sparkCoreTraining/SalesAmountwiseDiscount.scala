package training.sparkCoreTraining

import org.apache.spark.{SparkConf, SparkContext}

object SalesAmountwiseDiscount {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster(args(0))
      .setAppName(getClass.getName)
    val sc = new SparkContext(conf)

    sc.setLogLevel("ERROR")

    val fileRDD=sc.textFile(args(1))

    //  fileRDD.foreach(println)


    val schemaRDD=fileRDD.map(rec => {
      val Arr=rec.split(",")
      (Arr(0),Arr(3).toDouble)
    })

    val discnt=schemaRDD.reduceByKey(_+_)

    // val test=discnt.foreach(println)

    val discountAmountRDD=discnt.map( l => {
      if(l._2 > 1000)(l._1,l._2*0.9)
      else(l._2)
    })

    discountAmountRDD.foreach(println)

  }

}
