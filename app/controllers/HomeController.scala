package controllers

import javax.inject._

import database.MongoDB
import model.Person
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit ec: ExecutionContext) extends MongoDB() {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */


  def index = Action {
    read()
    Ok(views.html.index("Your new application is ready."))
  }

  val personForm: Form[Person] = Form {
    mapping(
      "name" -> text,
      "surname" -> text,
      "hit" -> text
    )(Person.apply)(Person.unapply)
  }

  def createPerson = Action.async {

    implicit request =>
      personForm.bindFromRequest().fold(

        //error
        formWithErrors => {
          Logger.error("form has error")
          Future(Ok(views.html.index("Your new application is ready.")))
        },

        //no error
        person => {
          create(person)
          read()
          //Future.successful(Ok(views.html.index(s"Artist ${person.name} added")))
          Future(Redirect(routes.HomeController.index()))
        }

      )
  }

  def readPersons = Action.async {
    implicit request =>
      Future(Ok(read()))
  }

  def updatePerson(name: String, newName: String, newSurname: String, newHit: String) = Action.async {
    implicit request =>
      update(name, newName, newSurname, newHit)
      Future(Ok(read()))

  }


  def deletePerson(name: String) = Action.async {
    implicit request =>
      delete(name)
      Future(Ok(read()))
  }

}

