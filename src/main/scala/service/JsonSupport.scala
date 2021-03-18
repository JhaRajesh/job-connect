package service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import service.ItemService.ClientData
import service.MongoStorageHandler.ClientDataMapper
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  /*trait JsonFormatSupports extends SprayJsonSupport with DefaultJsonProtocol*/

  implicit val clientDataMapperFormats: RootJsonFormat[ClientDataMapper] = jsonFormat2(ClientDataMapper.apply)
  implicit val clientDataFormats: RootJsonFormat[ClientData] = jsonFormat1(ClientData.apply)
}
