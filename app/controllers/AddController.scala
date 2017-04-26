package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * Created by zlatan on 26.4.17..
  */
class AddController @Inject extends Controller{
  def index = Action {
    Ok(views.html.index("Successfully added item!"))
  }
}
