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
import argonaut._, Argonaut._
import scalaj.http._
import scalaz._, Scalaz._
import java.io._
import PocketFormat._
import PocketJsonToPocketFormat._
/**
 * @author Salom
 */
object GetPocket extends App {

  val consumerKey = "11111-111111111111111111111111"
  val accessToken = "11111111-1111-1111-1111-111111"
  val filePath = """C:\Users\Salom\Desktop\ankiImport.txt"""

  val pocketRequest = PocketRequest(consumerKey, accessToken, Some("all"))

  val httpResponse = Http("https://getpocket.com/v3/get")
    .header("Content-Type", "application/json")
    .header("X-Accept", "application/json")
    .option(HttpOptions.readTimeout(10000))
    .postData(pocketRequest.asJson.nospaces).asString

  val body = httpResponse.body

  val decodeResult: String \/ PocketResponse =
    Parse.decodeEither[PocketResponse](body)

  decodeResult match {
    case -\/(a) => println(a)
    case \/-(a) => writeToFileInAnkiFormat(a, filePath)
  }

  def writeToFileInAnkiFormat(response: PocketResponse, filePath: String) {
    val writer = new PrintWriter(new File(filePath))
    toAnkiFormat(response).writeTo(writer)
    writer.close()
  }

  def toAnkiFormat(res: PocketResponse): AnkiFormat = {
    val v = res.list.items.map { x =>
      {
        if (x.givenTitle != "")
          StandardAnkiEntry(x.givenTitle, x.itemId, x.excerpt, x.givenUrl, x.resolvedUrl)
        else SimpleAnkiEntry(x.itemId, x.givenUrl, x.resolvedUrl)
      }
    }
    AnkiFormat(v)
  }
}



