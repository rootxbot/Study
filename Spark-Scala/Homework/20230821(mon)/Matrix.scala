object Matrix {
  def main(args: Array[String]): Unit = {
    type Matrix = Array[Array[Int]]

    object MatrixOperations {
      def multiply(matrix1: Matrix, matrix2: Matrix): Matrix = {
        val rows1 = matrix1.length
        val cols1 = matrix1(0).length
        val rows2 = matrix2.length
        val cols2 = matrix2(0).length

        if (cols1 != rows2) throw new IllegalArgumentException("Incompatible matrices for multiplication")

        val result = Array.ofDim[Int](rows1, cols2)

        for (i <- 0 until rows1) {
          for (j <- 0 until cols2) {
            result(i)(j) = (0 until cols1).map(k => matrix1(i)(k) * matrix2(k)(j)).sum
          }
        }
        result
      }
    }

    // Implicit class for operator overloading
    implicit class MatrixEnhancements(matrix1: Matrix) {
      def *(matrix2: Matrix): Matrix = MatrixOperations.multiply(matrix1, matrix2)
    }

    // 사용 예제
    val matrixA: Matrix = Array(Array(1, 2), Array(3, 4))
    val matrixB: Matrix = Array(Array(2, 0), Array(1, 3))

    val resultMatrix = matrixA * matrixB
    resultMatrix.foreach(row => println(row.mkString(", ")))
  }
}