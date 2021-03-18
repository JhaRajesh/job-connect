package service

import akka.event.jul.Logger
import service.ItemService.ClientData
import spray.json.RootJsonFormat

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ItemService {
  private val mongoStorageHandler = new MongoStorageHandler
  val repository = mutable.Map.empty[String, ClientData]
  def addItem(clientId: String, data: ClientData) = {
    mongoStorageHandler.addItemToCollection(clientId, data).map { _ => (): Unit }
  }

  def updateItem(clientId: String, clientData: ClientData) = {
    mongoStorageHandler.addOrUpdateItemToCollection(clientId, clientData).map { _ => (): Unit}
  }
}

object ItemService {
  case class ClientData(name: String)
}
