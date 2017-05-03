package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * Created by zlatan on 26.4.17..
  */
class AddController extends Controller{
  def add(name: String) = Action {
    Ok(views.html.index(name))
  }
}
