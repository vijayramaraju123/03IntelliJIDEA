package training.scalaTraining

import training.scalaTraining.Inheritance1.{Child1, Child2, GrandChild, Parent}

object Inheritance {

    def main(args: Array[String]): Unit = {

      val c = new Child1
      println(c.house)
      c.info()
      c.display()

      val p = new Parent
      p.info()

      //MultiLevel Inheritance

      println("MultiLevel Inheritance")

      val gc = new GrandChild
      gc.info()
      gc.display()

      //Hireachicial

      val c1 = new Child1
      c1.display()
      c1.info()

      val c2 = new Child2
      c2.display()
      c2.info()

    }
  }

