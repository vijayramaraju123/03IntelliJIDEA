package training.sparkSQLTraining.RealTimeScenario

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types.{DataTypes, DateType, DoubleType, StringType, StructField, StructType}
import training.sparkSQLTraining.readingJsonFile.getClass
import org.apache.spark.sql.functions.{date_format, _}

object amazonData {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local")
      .appName(getClass.getName)
      .getOrCreate()

    val userSchema = StructType(Array
    (StructField("State", StringType, false),
        StructField("product",StringType,true),
      StructField("Date" ,StringType, false)))

    val optionsMap = Map (
      "header" -> "true",
      "inferSchema" -> "true",
      "delimiter" -> ",",
      "dateFormat" -> "yyyy-MM-dd HH:mm:ss.S",
    "mode" -> "FAILFAST")


    // FACT TABLE
    val amazonDF = spark.read.format("csv")
      .options(optionsMap)
      .schema(userSchema)
      .load("C:\\Users\\Incredible\\Documents\\run\\Amazon-data.csv")
   // val display = amazonDF.show()
  //  amazonDF.printSchema()

    val amazonDFfinal = amazonDF.withColumn("Date",
      to_date(col("Date"), "dd-MM-yyyy"))

val FinalProd1 = amazonDFfinal.show()
    amazonDFfinal.printSchema()

    /*
    +-----+-------+----------+
|State|product|      Date|
+-----+-------+----------+
|   AP|     tv|2020-02-24|
|   KA|  table|2020-02-24|
|   AP|     tv|2020-02-13|
|   KA|    fan|2020-02-24|
|   MP|  table|2020-06-04|
|   AP|grinder|2020-06-24|
|   MP|    fan|2020-06-13|
|   MP|     tv|2020-02-13|
|   KA| fridge|2020-08-13|
|   MP|grinder|2020-08-29|
|   AP|    fan|2020-06-04|
|   MP|  table|2019-05-12|
|   KA| fridge|2019-06-25|
|   MP|  table|2019-11-02|
|   KA|grinder|2019-03-11|
|   MP|    fan|2019-07-19|
|   AP|     tv|2019-11-26|
|   MP| fridge|2019-11-02|
|   KA|grinder|2019-02-24|
|   MP|    fan|2019-02-13|
+-----+-------+----------+
only showing top 20 rows

root
 |-- State: string (nullable = true)
 |-- product: string (nullable = true)
 |-- Date: date (nullable = true)
     */

    val listDF=amazonDF.groupBy("State").
      agg(count("product").as("product")
        ,countDistinct("product").as("distinc_Product"),
        collect_set("product").as("setofItems"),
        collect_list("product").as("listOfItems"))

    // DIMENSION TABLE
    val userSchemacost = StructType( Array
    (StructField("product", StringType, true),
      StructField("productCost",DoubleType,false)))

    val amazoncostDF = spark.read.format("csv")
      .options(optionsMap)
      .schema(userSchemacost)
      .load("C:\\Users\\Incredible\\Documents\\run\\Amazon-cost.csv")
    val show = amazoncostDF.show()

    /*
    +-------+-----------+
|product|productCost|
+-------+-----------+
|     tv|     3200.0|
| fridge|      500.0|
|    fan|      643.0|
|  table|      257.0|
|grinder|      557.0|
+-------+-----------+
     */


    val join1 = amazoncostDF.join(amazonDFfinal,amazoncostDF("product") === amazonDFfinal("product"), "inner").show()

    /*
    +-------+-----------+-----+-------+----------+
|product|productCost|State|product|      Date|
+-------+-----------+-----+-------+----------+
|     tv|     3200.0|   AP|     tv|2020-02-24|
|  table|      257.0|   KA|  table|2020-02-24|
|     tv|     3200.0|   AP|     tv|2020-02-13|
|    fan|      643.0|   KA|    fan|2020-02-24|
|  table|      257.0|   MP|  table|2020-06-04|
|grinder|      557.0|   AP|grinder|2020-06-24|
|    fan|      643.0|   MP|    fan|2020-06-13|
|     tv|     3200.0|   MP|     tv|2020-02-13|
| fridge|      500.0|   KA| fridge|2020-08-13|
|grinder|      557.0|   MP|grinder|2020-08-29|
|    fan|      643.0|   AP|    fan|2020-06-04|
|  table|      257.0|   MP|  table|2019-05-12|
| fridge|      500.0|   KA| fridge|2019-06-25|
|  table|      257.0|   MP|  table|2019-11-02|
|grinder|      557.0|   KA|grinder|2019-03-11|
|    fan|      643.0|   MP|    fan|2019-07-19|
|     tv|     3200.0|   AP|     tv|2019-11-26|
| fridge|      500.0|   MP| fridge|2019-11-02|
|grinder|      557.0|   KA|grinder|2019-02-24|
|    fan|      643.0|   MP|    fan|2019-02-13|
+-------+-----------+-----+-------+----------+
only showing top 20 rows
     */

    val joinDF= amazoncostDF.join(amazonDFfinal, Seq("product"),"inner")
    val newOne = joinDF
    val test = newOne.select("State","product","productCost","Date")


    // group by state
    val grpSt = test.groupBy("State", "product")
      .agg(sum("productCost")).show()
/*
+-----+-------+----------------+
|State|product|sum(productCost)|
+-----+-------+----------------+
|   AP| fridge|           500.0|
|   AP|     tv|          9600.0|
|   MP|grinder|          1671.0|
|   AP|  table|           257.0|
|   AP|    fan|          1929.0|
|   KA|    fan|          2572.0|
|   MP|    fan|          3215.0|
|   KA|grinder|          1671.0|
|   KA|  table|          1799.0|
|   MP|  table|          2313.0|
|   AP|grinder|          2785.0|
|   MP| fridge|           500.0|
|   MP|     tv|          3200.0|
|   KA| fridge|          1000.0|
+-----+-------+----------------+
 */
    val latestDF = test.withColumn("rank",
      rank().over(Window.partitionBy("State", "product")
        .orderBy(col("Date").cast("date").desc))).show()
/*
+-----+-------+-----------+----------+----+
|State|product|productCost|      Date|rank|
+-----+-------+-----------+----------+----+
|   AP| fridge|      500.0|2020-06-04|   1|
|   AP|     tv|     3200.0|2020-02-24|   1|
|   AP|     tv|     3200.0|2020-02-13|   2|
|   AP|     tv|     3200.0|2019-11-26|   3|
|   MP|grinder|      557.0|2020-08-29|   1|
|   MP|grinder|      557.0|2020-06-04|   2|
|   MP|grinder|      557.0|2020-02-24|   3|
|   AP|  table|      257.0|2019-11-02|   1|
|   AP|    fan|      643.0|2020-06-04|   1|
|   AP|    fan|      643.0|2020-06-04|   1|
|   AP|    fan|      643.0|2020-02-24|   3|
|   KA|    fan|      643.0|2020-02-24|   1|
|   KA|    fan|      643.0|2020-02-24|   1|
|   KA|    fan|      643.0|2020-02-13|   3|
|   KA|    fan|      643.0|2020-02-13|   3|
|   MP|    fan|      643.0|2020-06-13|   1|
|   MP|    fan|      643.0|2020-02-13|   2|
|   MP|    fan|      643.0|2019-07-19|   3|
|   MP|    fan|      643.0|2019-07-19|   3|
|   MP|    fan|      643.0|2019-02-13|   5|
+-----+-------+-----------+----------+----+
only showing top 20 rows
 */

    val latestDF1 = test.withColumn("rank",
      rank().over(Window.partitionBy("State", "product")
        .orderBy(col("State").desc)))

    val finalDF5 = latestDF1.withColumn("disocuntedTotalAmt",
      when(col("productCost") > 1000, col("productCost")*0.9)
        .otherwise(col("productCost")))

    val latestDF6 = finalDF5.withColumn("rank",
      rank().over(Window.partitionBy("State")
        .orderBy(col("productCost").desc))).show()

    /*
    +-----+-------+-----------+----------+----+------------------+
|State|product|productCost|      Date|rank|disocuntedTotalAmt|
+-----+-------+-----------+----------+----+------------------+
|   KA|    fan|      643.0|2020-02-24|   1|             643.0|
|   KA|    fan|      643.0|2020-02-13|   1|             643.0|
|   KA|    fan|      643.0|2020-02-24|   1|             643.0|
|   KA|    fan|      643.0|2020-02-13|   1|             643.0|
|   KA|grinder|      557.0|2019-03-11|   5|             557.0|
|   KA|grinder|      557.0|2019-02-24|   5|             557.0|
|   KA|grinder|      557.0|2020-02-24|   5|             557.0|
|   KA| fridge|      500.0|2020-08-13|   8|             500.0|
|   KA| fridge|      500.0|2019-06-25|   8|             500.0|
|   KA|  table|      257.0|2020-02-24|  10|             257.0|
|   KA|  table|      257.0|2020-02-24|  10|             257.0|
|   KA|  table|      257.0|2020-02-24|  10|             257.0|
|   KA|  table|      257.0|2019-05-12|  10|             257.0|
|   KA|  table|      257.0|2019-11-26|  10|             257.0|
|   KA|  table|      257.0|2020-02-24|  10|             257.0|
|   KA|  table|      257.0|2020-02-13|  10|             257.0|
|   AP|     tv|     3200.0|2020-02-24|   1|            2880.0|
|   AP|     tv|     3200.0|2020-02-13|   1|            2880.0|
|   AP|     tv|     3200.0|2019-11-26|   1|            2880.0|
|   AP|    fan|      643.0|2020-06-04|   4|             643.0|
+-----+-------+-----------+----------+----+------------------+
only showing top 20 rows
     */

    val sumProd= test.withColumn("SumofProducts",
    sum("productCost").over(Window.partitionBy("State", "product")
    .orderBy("State")))
    val sumProd1 =sumProd.show()

    val updatedDF=sumProd.groupBy("State").agg(
    collect_set("SumofProducts").as("distinctItems")).orderBy("State").show(false)

    /*
+-----+---------------------------------------+
|State|distinctItems                          |
+-----+---------------------------------------+
|AP   |[2785.0, 257.0, 1929.0, 500.0, 9600.0] |
|KA   |[2572.0, 1000.0, 1799.0, 1671.0]       |
|MP   |[2313.0, 3215.0, 3200.0, 1671.0, 500.0]|
+-----+---------------------------------------+
     */

    val updatedDF1=sumProd.groupBy("State","product").agg(
      collect_set("SumofProducts").as("distinctItems")).orderBy("State")

    /*
    val updatedDF2=updatedDF1.show()

+-----+-------+-------------+
|State|product|distinctItems|
+-----+-------+-------------+
|AP   |table  |[257.0]      |
|AP   |tv     |[9600.0]     |
|AP   |fan    |[1929.0]     |
|AP   |grinder|[2785.0]     |
|AP   |fridge |[500.0]      |
|KA   |table  |[1799.0]     |
|KA   |grinder|[1671.0]     |
|KA   |fan    |[2572.0]     |
|KA   |fridge |[1000.0]     |
|MP   |grinder|[1671.0]     |
|MP   |table  |[2313.0]     |
|MP   |fan    |[3215.0]     |
|MP   |fridge |[500.0]      |
|MP   |tv     |[3200.0]     |
+-----+-------+-------------+
     */


    val test1= updatedDF1.toDF().show()

    val grpSt1 = test.groupBy("State")
      .agg(sum("productCost")).where("Date >= 2015-05-22 && Date <= 2020-05-20" )



    /*
  val joind= amazoncostDF.alias("a").join(amazonDF.alias("b"),
    (amazoncostDF['product'] == amazonDF['product']).select( "a.productCost", "b.State")
*/



    import spark.implicits._

// val finalDF = newDf.select(col("State"),column("product"), $"productCost").show()

    /*
val listItemsDF = amazonDF.withColumn("ListofItems", collect_list("product").as("ListOfItems")).show()
    val distinctItemsDF = amazonDF.withColumn("ListofItems", collect_set("product").as("ListOfItems")).show()
  */

  }


}
