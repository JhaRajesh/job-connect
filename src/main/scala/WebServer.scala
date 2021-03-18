import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import service.ItemService.ClientData
import service.{ItemService, PaginationService}

import scala.io.StdIn

object WebServer {

  import  service.JsonSupport._
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val paginationService = new PaginationService
    val itemService = new ItemService

    val route = pathPrefix("api" / "v1" ) {
      get {
        pathPrefix("getItems"){
          parameter("pageNumber".as[Long], "limit".as[Long]){ (pageNo, limit) =>
            println(s"page number is $pageNo")
            complete(paginationService.getNextItems(pageNo, limit).map { result =>
              s"$result"
            })
          }
        }
      } ~
        put {
          pathPrefix("updateItem" / Segment / "update") { clientId =>
            val clientData = ClientData("test")
            println(s"client data is $clientId and data is $clientData")
            complete(itemService.updateItem(clientId, clientData).map { res => "Updated"})
            /*entity(as[ClientData]){ clientData =>
              println(s"client data is $clientId and data is $clientData")
              complete(itemService.updateItem(clientId, clientData).map { res => "Updated"})
            }*/
          }
        }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}


