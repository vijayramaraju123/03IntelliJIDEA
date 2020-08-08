package training.scalaTraining.GIthub

object CyclicRotation {

  object CyclicRotation extends App {
    // 100%
    def solution(A: Array[Int], K: Int): Array[Int] = {
      def rotateKStep(l: List[Int], K: Int): List[Int] = {
        def rotateOneStep(l: List[Int]) = {
          l.take(l.size - 1).+:(l.last)
        }

        if (K == 0) l
        else rotateKStep(rotateOneStep(l), K - 1)
      }

      if (A.isEmpty) A
      else rotateKStep(A.toList, K).toArray
    }

    println(solution(Array(3, 8, 9, 7, 6, 8), 3).toList)

    val a = Array(3, 8, 9, 7, 6, 8)
    val n = 3

    println((a.takeRight(n) ++ a.dropRight(n)).toList)

  }

}