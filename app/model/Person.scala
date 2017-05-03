package model

import play.api.libs.json.Json

/**
  * Created by zlatan on 3.5.17..
  */

case class Person(name: String, surname: String, hit: String)

object  Person{
    implicit val personFormat = Json.format[Person]
}
