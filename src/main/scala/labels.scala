package lin567_p1

case class Language( language:String ) {
  override def toString = language
}

case class Document( id:String, text:Array[String], language:Language )



