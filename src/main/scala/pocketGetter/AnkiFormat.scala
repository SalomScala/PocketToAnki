/**
    This file is part of PocketToAnki.
    PocketToAnki is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.
    PocketToAnki is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with PocketToAnki. If not, see <http://www.gnu.org/licenses/>.
 */
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