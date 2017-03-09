package controllers


import com.google.inject.Inject
import models.PersonSignup
import play.api._
import play.api.mvc._

class HomeController @Inject() extends Controller {


  def index = Action { implicit  request =>
    Ok(views.html.signin())
  }

  def logout = Action { implicit  request =>
    Ok(views.html.signin()).withNewSession.flashing("message" -> "You have successfully logged out!")
  }

}
