package controllers


import java.sql._
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db._
import UserForm._
import anorm._

@Singleton
class HomeController @Inject()(db: Database, cc: MessagesControllerComponents)
    extends MessagesAbstractController(cc) {

      def index() = Action {implicit request =>
      db.withConnection { implicit conn =>
        val result:List[Any] = SQL("Select * from user")
          .as(SqlParser.str("name").
            ~(SqlParser.str("message")).*)
        for(item <- result){
          println(item)
        }
        Ok(views.html.index(
          "User Data.",result
        ))
      }
    }

  def add() = Action {implicit request =>
    Ok(views.html.add(
      "つぶやこう",
      form
    ))
  }

  def create() = Action {implicit request =>
    val formdata = form.bindFromRequest
    val data = formdata.get
    try
      db.withConnection { conn =>
        val ps = conn.prepareStatement(
          "insert into user values (default, ? ,?)")
          ps.setString(1, data.name)
          ps.setString(2, data.message)
          ps.executeUpdate

      }
      catch {
        case e: SQLException =>
        Ok(views.html.add(
          "つぶやきを入力してください",
          form
        ))
      }
      Redirect(routes.HomeController.index)
  }


}
