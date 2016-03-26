package lin567_p1


class NaiveBayes {
  // Use these to compute P( Language )
  var docLanguageCounts = Map[Language,Double]().withDefaultValue(0D)
  var docCount = 0D
  var words = 0D

  // Use these to compute P( Word | Language )
  var languageWordCounts = Map[Tuple2[Language,String],Double]().withDefaultValue(0D)

//  Count of ngrams per language
  var wordCountPerLanguage = Map[Language,Double]().withDefaultValue(0D)

  // This should increment counts so you can compute P( Language ) and P( Word | Language )
  def train( corpus:Set[Document] ) {
    // This loops over the set of documents, and provides variables for the document id as a String,
    // the document text as an Array[String], and the language as a Language
    corpus.foreach{ case Document( id, text, language ) =>
      // Implement me

      //docCount  COUNT 1
      docCount+=1

      //docLanguageCounts  COUNT 2
      val key = language
      var new_value = 0D
//      println(id)
//      text.foreach(println)
//      println(language)
      if (docLanguageCounts.contains(key)){
        new_value = docLanguageCounts(key) + 1
      }else{
        new_value = 1
      }
      docLanguageCounts += (key -> new_value)

      //languageWordCounts COUNT 3
      text.foreach { line =>
        val t = (language, line)
        var count = 0D
        words+=1
          if (languageWordCounts.contains(t)){
            count = languageWordCounts(t) + 1
          }else{
            count = 1
          }
        languageWordCounts += (t -> count)

        var count1 = 0D
        if (wordCountPerLanguage.contains(language)){
          count1 = wordCountPerLanguage(language) + 1
        }else{
          count1 = 1
        }
        wordCountPerLanguage += (language -> count1)

      }

    }
    println(docCount)
    println(docLanguageCounts.take(22))
    println(languageWordCounts.take(60))
    println(wordCountPerLanguage.take(25))
  }

  // Should compute P( word | language ). Implement with add-lambda smoothing.
  def p_wordGivenLg( word:String, language:Language, lambda:Double ) = {
    // IMPLEMENT ME
//    count of words in a language / total number of words in the language SHOULD THIS BE NUMBER OF DOCUMENTS????
//    println(  languageWordCounts((language,word))/wordCountPerLanguage(language))
//    ADD LAMBDA SMOOTHING
    //prob of lang1 given word * prob of lang2 given word ... * prob of word / prob of evidence
//    count of word in language1/total words in language1

//    languageWordCounts((language,word))/wordCountPerLanguage(language)

    (languageWordCounts(language,word)+lambda)/(wordCountPerLanguage(language)+lambda*docCount)
//    languageWordCounts((language,word))/(docCount)
  }

  // Should compute P( Language )
  def p_Lg( language:Language ) = {
    // IMPLEMENT ME
    //Count of docs in language1/ total number of docs in all languages
    docLanguageCounts(language)/docCount

  }


  // Should compute P( Word, Language )= P( Language )\prod_{Word in Document}P( Word | Language )
  def p_docAndLg( document:Array[String], language:Language, lambda:Double ) = {
    // IMPLEMENT ME
    var conditionalProbability = 1D
    for(word <- document){
      conditionalProbability *= p_wordGivenLg(word,language,lambda)
    }
    conditionalProbability * p_Lg(language)
//    p_Lg(language) * conditionalProbability
  }


  // This function takes a document as a parameter, and returns the highest scoring language as a
  // Language object. 
  def mostLikelyLanguage( document:Array[String], lambda:Double ) = {
    // Loop over the possible languages (they should accessible in docLanguageCounts.keys), and find
    // the language with the highest P( Document, Language ) score
    var maxLang = ""
    var max = 0D
  for(lang <- docLanguageCounts.keys){
    val current = p_docAndLg(document,lang,lambda)
    if(current >=  max){
      max = current
      maxLang = lang.language
    }
  }
    Language(maxLang)
  }
}

