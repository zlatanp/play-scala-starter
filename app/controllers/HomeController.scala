package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import model.Person.personFormat
import model.Person
import play.api.data.Form
import play.api.data.Forms._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import reactivemongo.api.ReadPreference
import reactivemongo.play.json._
import reactivemongo.play.json.collection.{
JSONCollection, JsCursor
}, JsCursor._
import reactivemongo.api.Cursor
import reactivemongo.bson._
import reactivemongo.api.collections.bson.BSONCollection

import scala.concurrent.{ExecutionContext, Future}
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (val reactiveMongoApi: ReactiveMongoApi)(implicit ec:ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  val collectionF:Future[JSONCollection] = database.map(_.collection[JSONCollection]("artists"))

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  val personForm: Form[Person] = Form {
    mapping(
      "name" -> text,
      "surname" -> text,
      "hit" -> text
    )(Person.apply)(Person.unapply)
  }

  def addPerson = Action.async {

    implicit request =>
      personForm.bindFromRequest().fold(

        //error
        formWithErrors => {
          Logger.error("form has error")
          Future(Ok(views.html.index("Your new application is ready.")))
        },

        //no error
        person => {
          collectionF.map(_.insert(person))
          Future.successful(Ok(views.html.index(s"Artist ${person.name} added")))
        }

      )
  }

  def getPersons = Action.async {
    implicit request =>

      val query  = Json.obj("name" -> "test1")
      val kursor = collectionF.map(_.find(query).cursor[List[Person]]())
      val all = kursor.collect[List[Person]] _

      //Future(println(all))
      Future.successful(Ok(views.html.index(all.toString())))
  }

}

