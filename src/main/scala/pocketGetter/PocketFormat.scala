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