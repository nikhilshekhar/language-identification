package lin567_p1


object IO {

  def extractNGrams( text:String, n:Int ) = {
    // pad the string with document boundary markers, and return an array of a sliding window over
    // the characters in the string
    ("#"*(n-1) + text + "#"*(n-1)).sliding(n).toArray
  }

  def readDocuments( corpusPath:String, n:Int ) =
    io.Source.fromFile( corpusPath ).getLines.map{ line =>

      val Array( id, text, label) = line.split("""\|""")
      val characterNGrams = extractNGrams( text, n )

      Document( id, characterNGrams, Language( label ) )
    }.toSet


}

