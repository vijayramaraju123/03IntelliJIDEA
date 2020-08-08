package training.sparkSQLTraining.RealTimeScenario

import org.apache.spark.sql.SparkSession

object importMysqlDataBase {

  /*

  def main(args: Array[String]): Unit = {
    val spark =SparkSession.builder()
      .master("local")
      .appName(getClass.getName)
      .getOrCreate()

    val connMap= Map( "url" -> "jdbc:mysql://localhost:3306/ecommerce",
      "dbtable", "sales",
      "username" -> " root",
      "password" -> "cloudera")

    val connRDD= spark.read.format("jdbc").options("connMap").load()
    connRDD.printSchema()
    connRDD.show()
    println(connRDD.getNumPartitions)

    val collMap = Map ( "url" -> " jdbc:mysql://localhost:3306/ecommerce",
    "dbtable" -> "(select * from sales where customerId = 4) tmp ",
    "username" -> "root",
    "password" -> "cloudera")

    val incRdd = spark.read.format("jdbc").options("collMap").load()


  }
*/
}

