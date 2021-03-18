package models

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import service.MongoStorageHandler.ClientDataMapper

object MongoConnection {
  val mongoClient: MongoClient = MongoClient("mongodb://localhost")
  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[ClientDataMapper]), DEFAULT_CODEC_REGISTRY )
  val database: MongoDatabase = mongoClient.getDatabase("mydb").withCodecRegistry(codecRegistry)


  val itemCollection: MongoCollection[ClientDataMapper] = database.getCollection("items")
}
