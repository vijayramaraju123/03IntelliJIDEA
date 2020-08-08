package training.sparkSQLTraining.RealTimeScenario

import org.apache.spark.sql.{DataFrame, SparkSession}

case class SalesRecord (tid:Int,cid:Int,itemid:Int,price:Double)

object rddToDF {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(getClass.getName)
      .getOrCreate()


    val textRdd= spark.sparkContext.textFile("C:\\Users\\Incredible\\Documents\\run\\sales.csv")

    val filterRdd= textRdd.filter( l => ! l.startsWith("transactionId"))


      val schemaRdd = filterRdd.map(r => {
        val arr = r.split(",")
        SalesRecord(arr(0).toInt, arr(1).toInt,
          arr(2).toInt, arr(3).toDouble)
      })
      schemaRdd.foreach(println)

      import spark.implicits._
      val toDF = schemaRdd.toDF()
      toDF.show()
      toDF.printSchema()

      val salesDF = spark.createDataFrame(schemaRdd)
      salesDF.show()



  }


}
