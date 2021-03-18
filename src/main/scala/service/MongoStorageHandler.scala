package service

import models.MongoConnection
import org.mongodb.scala.bson.{BsonDocument, ObjectId}
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.Filters
import org.mongodb.scala.result.UpdateResult
import service.ItemService.ClientData
import service.MongoStorageHandler.ClientDataMapper

import scala.concurrent.ExecutionContext.Implicits.global


class MongoStorageHandler {
  private val itemCollection = MongoConnection.itemCollection
  def addItemToCollection(clientId: String, clientData: ClientData) = {
    val clientDataMapper = ClientDataMapper(clientId, clientData.name)
    itemCollection.insertOne(clientDataMapper).toFuture()
  }

  def addOrUpdateItemToCollection(clientId: String, clientData: ClientData) = {
    val query: BsonDocument = BsonDocument("clientId" -> clientId )

    for {
       _ <- itemCollection.insertOne(ClientDataMapper(clientId, clientData.name)).toFuture()
    } yield (): Unit
  }

  def getItemFromCollection(clientId: String) = {
    itemCollection.find(BsonDocument("clientId" -> clientId)).first().toFuture()
  }
}

object MongoStorageHandler {
  case class ClientDataMapper(_id: ObjectId, clientId: String, name: String)

  object ClientDataMapper {
    def apply(clientId: String, name: String): ClientDataMapper = {
      ClientDataMapper(new ObjectId(), clientId, name)
    }
  }
}
