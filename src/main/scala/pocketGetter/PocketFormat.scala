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

/**
 * @author Salom
 */
object PocketFormat {
  type ConsumerKey = String
  type AccessToken = String
  type RedirectionURL = String
  type Status = Int
  type Complete = Int
  type State = String
  type ItemId = String
  type Title = String
  type URL = String

  case class PocketRequest(key: ConsumerKey, token: AccessToken, state: Option[State])
  case class Item(itemId: ItemId, resolvedId: ItemId, givenTitle: Title, givenUrl: URL, resolvedTitle: Title, resolvedUrl: URL, excerpt: String)
  case class ItemList(items: List[Item])
  case class PocketResponse(status: Status, complete: Complete, list: ItemList)
}