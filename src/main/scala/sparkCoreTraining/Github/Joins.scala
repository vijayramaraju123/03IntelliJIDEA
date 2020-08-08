package training.sparkCoreTraining.Github

import org.apache.spark.{SparkConf, SparkContext}

object Joins {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(getClass.getName)
    val sc = new SparkContext(conf)

    sc.setLogLevel("ERROR")

    val as = List((101, ("Hanson", "Bart")),
      (102, ("Thomas", "Clipper")),
      (103, ("John", "ClipperVisa")),
      (104, ("Chu", "Clipper")))
    val subscriptions = sc.parallelize(as,4) // Pair Rdd with key = customer_Id, value = (last_name, subsription_card_name)

    println(subscriptions.getNumPartitions)
    subscriptions.glom().collect()
    println(subscriptions.glom().map(u => u.length).collect())

    // List containing (customer_id, most_visit

    // List containing (customer_id, most_visited_city). Contains all customer who use cards and thus can be tracked.
    val ls = List((101, "Chicago"),
      (101, "SanFranciso"),
      (102, "SantaClara"),
      (102, "SanJose"),
      (103, "MountainView"),
      (103, "Monterey"))
    val locations = sc.parallelize(ls,4)  // Pair Rdd with key = customer_Id, value = most_visited_city
    println(locations.getNumPartitions)

    // To find that have a subscription as well as location info, we can call inner join:
    val innerJoinedRdd = subscriptions.join(locations)
    println("subscriptions.join(locations)")
    innerJoinedRdd.collect().foreach(println)

    // NOTE: we could have also called locations.join(subscriptions) for the same result
    val innerJoinedRdd2 = locations.join(subscriptions)
    println("locations.join(subscriptions)")
    innerJoinedRdd2.collect().foreach(println)

    //Left outer join
    // List containing (customer_id, (last_name, subsription_card_name))
    val as1 = List((101, ("Hanson", "Bart")),
      (102, ("Thomas", "Clipper")),
      (103, ("John", "ClipperVisa")),
      (104, ("Chu", "Clipper")))

    val subscriptions1 = sc.parallelize(as1) // Pair Rdd with key = customer_Id, value = (last_name, subsription_card_name)

    // List containing (customer_id, most_visited_city). Contains all customer who use cards and thus can be tracked.
    val ls1 = List((101, "Chicago"),
      (101, "SanFranciso"),
      (102, "SantaClara"),
      (102, "SanJose"),
      (103, "MountainView"),
      (103, "Monterey"))

    val locations1 = sc.parallelize(ls1)  // Pair Rdd with key = customer_Id, value = most_visited_city

    // Here we have to call the leftOuterJoin, as we need all the customers who have subscriptions. The second element in the combination i.e. the value from the second list can be null, which is okay for our requirement.
    val leftOuterJoinedRdd = subscriptions1.leftOuterJoin(locations1)
    println("subscriptions1.leftOuterJoin(locations1)")
    leftOuterJoinedRdd.collect().foreach(println)


  }


}