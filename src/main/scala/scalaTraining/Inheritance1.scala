package training.scalaTraining

object Inheritance1 {


  class Parent {
    val house = "2BHK"

    def info() = println("infor function from parent class")
  }

  class Child1 extends Parent {
    def display() = println("display function from child class 1")
  }

  class Child2 extends Parent {
    def display() = println("display function from child class 2")
  }

  class Child3 extends Parent {
    def display() = println("display function from child class 3")
  }

  class GrandChild extends Child1 {

  }

}


