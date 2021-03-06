package database

import javax.inject._

import model.Person
import model.Person.personFormat
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JsCursor._
import reactivemongo.play.json.collection.{JSONCollection, JsCursor}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by zlatan on 17.5.17..
  */
class MongoDB @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit ec: ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents {

  val collectionF: Future[JSONCollection] = database.map(_.collection[JSONCollection]("artists"))
  var list: String = "null"

  def create(person: Person): Unit = {
    collectionF.map(_.insert(person))
    read()
  }

  def read(): String = {
    collectionF.map { coll =>
      val cursor: Cursor[Person] = coll.find(Json.obj()).cursor[Person]()
      val futurePersons: Future[List[Person]] = cursor.collect[List]()
      val futurePostsJsonArray: Future[JsArray] = futurePersons.map {
        Json.arr(_)
      }

      futurePostsJsonArray.map { personsjs =>
        list = personsjs.toString()
      }
    }
    list
  }

  def update(name: String, newName: String, newSurname: String, newHit: String): Unit = {
    val selector = BSONDocument("name" -> name)
    val mod = BSONDocument("$set" -> BSONDocument("name" -> newName, "surname" -> newSurname, "hit" -> newHit))
    collectionF.map(_.update(selector, mod))
    read()
  }

  def delete(name: String): Unit = {
    collectionF.map(_.findAndRemove(Json.obj("name" -> name)))
    read()
  }
}
