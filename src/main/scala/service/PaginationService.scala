package service

import service.PaginationService.itemsList
import spray.json._
import scala.concurrent.Future


class PaginationService {
  def getNextItems(pageNo: Long, pageLimit: Long): Future[List[Int]] = {
    val nextItems = itemsList.drop((pageNo-1).toInt*pageLimit.toInt)
    Future.successful(nextItems.take(pageLimit.toInt).toList)
  }
}

object PaginationService {
  val itemsList = 1 to 100


  case class PageItems(items: List[Int])

}
