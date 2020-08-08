package training.sparkSQLTraining

import org.apache.hadoop.hive.ql.exec.spark.session.SparkSession
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import training.sparkCoreTraining.Github.Joins.getClass


object readingCSVFile {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(getClass.getName)
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val salesRDD=sqlContext.read.format("csv")
      .option("inferSchema","true")
      .option("header","true")
      .option("delimiter",",")
      .load("C:\\Users\\Incredible\\Documents\\run\\sales.csv")
    salesRDD.show()

    //DSLAPI way
    val newDF=salesRDD.where("amountPaid > 1000").select("transactionId","customerId")
    newDF.show()
    newDF.printSchema()
    // SQL way


    salesRDD.registerTempTable("salesTbl")
    val newDF1 = sqlContext.sql("select transactionId,customerId from salesTbl where amountPaid > 1000")
    newDF1.show()
newDF1.printSchema()

    val options = Map (
      "header" -> "true",
      "inferSchema" -> "true",
      "delimiter" -> ","
    )

    val customerDF=sqlContext.read.format("csv")
        .options(options)
        .load("C:\\Users\\Incredible\\Documents\\run\\customers.csv")

    customerDF.show()
    customerDF.printSchema()


Thread.sleep(50000)
  }
}
