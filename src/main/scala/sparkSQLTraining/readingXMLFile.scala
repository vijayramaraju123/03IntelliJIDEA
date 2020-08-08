package training.sparkSQLTraining

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions._
import training.sparkSQLTraining.readingCSVFile.getClass


object readingXMLFile {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName(getClass.getName)
      .master("local")
      .getOrCreate()

    val optionsMap = Map (
      "rowTag" -> "person"
    )

    val xmlDF = spark.read
      .format("xml")
      .options(optionsMap)
      .load("C:\\Users\\Incredible\\Documents\\run\\ages.xml")

    xmlDF.show()
    xmlDF.printSchema()

    import spark.implicits._
    val flatDF = xmlDF.select(xmlDF("age._VALUE").as("Age"),column("age._birthplace").alias("BirthPlace"),col("age._born"),$"name")

    val flatDF1 = xmlDF.selectExpr("age._VALUE as age",
      "age._birthplace as birthplace1",
      "age._born as borndate1",
      "name as name1")
    flatDF1.show()

    val newColDF=flatDF.withColumn("location_flag", lit("Not Applicable")).show()

    val newColDF1=flatDF.withColumn("Conditionr", when((col("Age") < 27)  , "Elibigle"). otherwise ("Not Eligible")).show()


    val newColDF2=flatDF.withColumn("Conditionr",
      when((col("Age") < 27)  , "Young").
      when(col("Age") > 27 and col("Age") < 35, "audult")
      . otherwise ("old"))


    val renamedDF = newColDF2.withColumnRenamed("Conditionr", "MultCond")


val dropDF=renamedDF.drop("birthplace").drop("borndate").show()

    xmlDF.registerTempTable("v_agexml")
    spark.sql("select age._VALUE as age,age._birthplace as birthplace, age._born as borndate," + " name from v_agexml").show()



  }

}
