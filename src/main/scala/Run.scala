package lin567_p1

object Run {
  def main( args:Array[String] ) {
    val trainPath = args(0)
    val testPath = args(1)
    val n = args(2).toInt
    val lambda = args(3).toDouble

    val nbModel = new NaiveBayes

    val trainSet = IO.readDocuments( trainPath, n )
    val testSet = IO.readDocuments( testPath, n )

    nbModel.train( trainSet )

    testSet.foreach{ case Document( id, text, _ ) =>
      val bestLanguage = nbModel.mostLikelyLanguage( text, lambda )

      println( id + "|" + bestLanguage )
    }

  }
}

