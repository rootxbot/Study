object fibonnaci {
  def main(args: Array[String]):Unit = {
    def fibonnaciresult(size: Int): Array[Int] = {
      if (size <= 0) return Array() // size가 0이하면 빈 Array 반환

      val result = new Array[Int](size)
      for (i <- 0 until size) {
        result(i) = if (i < 2) i else result(i - 1) + result(i - 2)
      }
      result.toArray
    }

    println(fibonnaciresult(-1).isEmpty)
    println(fibonnaciresult(0).isEmpty)
    println(fibonnaciresult(1).toSeq == List(0))
    println(fibonnaciresult(2).toSeq == List(0, 1))
    println(fibonnaciresult(5).toSeq == List(0, 1, 1, 2, 3))
    println(fibonnaciresult(10).toSeq == List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
  }
}

