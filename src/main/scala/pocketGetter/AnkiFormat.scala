package pocketGetter

trait AnkiEntry {
  def writeTo(writer: java.io.Writer)
  def pocketUrl(pocketId: String, title: String) = {
    """"<a href=""https://getpocket.com/a/read/""" + pocketId + """"">""" + title + """</a> """"
  }
}

case class StandardAnkiEntry(title: String, pocketId: String, excerpt: String, originalUrl: String, resolvedUrl: String) extends AnkiEntry {
  def writeTo(writer: java.io.Writer) = {
    writer.write(pocketUrl(pocketId, title))
    writer.write("\t")
    writer.write(url)
    writer.write("<div>")
    writer.write(excerpt)
    writer.write("</div>")
    writer.write("\n")
  }
  def url = {
    """"<a href=""""" + originalUrl + """"">""" + resolvedUrl + """</a> """"
  }
}
case class SimpleAnkiEntry(pocketId: String, originalUrl: String, resolvedUrl: String) extends AnkiEntry {
  def writeTo(writer: java.io.Writer) = {
    writer.write(pocketUrl)
    writer.write("\t")
    writer.write(pocketUrl)
    writer.write("\n")
  }
  def pocketUrl: String = pocketUrl(pocketId, originalUrl)
  def url = {
    """"<a href=""""" + originalUrl + """"">""" + resolvedUrl + """</a> """"
  }

}
case class AnkiFormat(ankiEntries: List[AnkiEntry]) {
  def writeTo(writer: java.io.Writer) {
    ankiEntries.foreach { _.writeTo(writer) }
  }
}