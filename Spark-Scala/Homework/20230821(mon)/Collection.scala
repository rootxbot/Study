object Collection {
  def main(args: Array[String]):Unit = {
    val wordList: List[String] = List("apple", "basket", "candy")
    val totalLength: Int = wordList.map(_.length).sum
    var count: Int = 0
    var wordcount: Int = wordList.length
    println(totalLength)
    println(wordcount)
  }
}
