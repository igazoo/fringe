package controllers

object UserForm {
  import play.api.data._
  import play.api.data.Forms._

  case class Data(name: String, message: String)

  val form = Form(
    mapping(
      "name" -> text,
      "message" -> text
    )(Data.apply)(Data.unapply)
  )
}
