package training.sparkSQLTraining

import org.apache.spark.sql
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object readingJsonFile {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local")
      .appName(getClass.getName)
      .getOrCreate()

    val jsonDF= spark.read.format("json")
      .load("C:\\Users\\Incredible\\Documents\\run\\sales.json")


    //   DSLAPI way of wrting code
val groupDF=jsonDF.groupBy("customerId")
  .agg(sum("amountPaid").as("TotalAmount")
  ,count("itemId").as("totalItems"),
    collect_list("itemId").as("ListOfItems"),
    collect_set("itemId").as("distinctItems")).show()


    //SQL way of writing code
  //  jsonDF.createOrReplaceGlobalTempView("test")
  //  val test4 =spark.sql("select * from test")




  }

}
