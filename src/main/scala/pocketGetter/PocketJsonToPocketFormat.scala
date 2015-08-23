package pocketGetter

/**
 * @author Salom
 */
object PocketJsonToPocketFormat {
  import PocketFormat._
  import argonaut._, Argonaut._

  implicit def ItemCodecJson: CodecJson[Item] = casecodec7(Item.apply, Item.unapply)("item_id", "resolved_id", "given_title", "given_url", "resolved_title", "resolved_url", "excerpt")
  implicit def ItemListDecodeJson: DecodeJson[ItemList] =
    DecodeJson(c => {
      val res = c.focus.objectValues.map { x => x.map { y => y.as[Item] } }
      val res2 = res.map { x =>
        x.foldLeft(DecodeResult.ok(List[Item]()))((b: DecodeResult[List[Item]], a: DecodeResult[Item]) =>
          for {
            b1 <- b
            a1 <- a
          } yield b1.::(a1))
      }
      val res3 = res2.getOrElse(DecodeResult.ok(List[Item]())).map { x => ItemList(x) }
      res3
    })

  implicit def PersonDecodeJson: DecodeJson[PocketResponse] =
    DecodeJson(c => for {
      status <- (c --\ "status").as[Int]
      complete <- (c --\ "complete").as[Int]
      list <- (c --\ "list").as[ItemList]
    } yield PocketResponse(status, complete, list))

  implicit def PocketRequestCodecJson =
    casecodec3(PocketRequest.apply, PocketRequest.unapply)("consumer_key", "access_token", "state")

}