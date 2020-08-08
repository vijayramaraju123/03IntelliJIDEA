package training.sparkSQLTraining

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.hive.HiveContext

object NestedBookXml {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local")
      .appName(getClass.getName)
      .getOrCreate()

    val xmlDF  = spark.read.format("xml")
      .option("rowTag", "book")
      .load("C:\\Users\\Incredible\\Documents\\run\\books-nested-array.xml")

  val xmlDF1 = xmlDF.withColumn("publish_date", explode(col("publish_date")))
val tesShow=xmlDF1.show()

    val rankRDD=xmlDF1.withColumn("rank",
      rank().over(Window.partitionBy("_id")
        .orderBy(col("publish_date").cast("date").desc)))
    val testSh=rankRDD.show()

  val finalDF=rankRDD.where("rank=1").drop("rank").show()


  }
}
