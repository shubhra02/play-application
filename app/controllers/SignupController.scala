package controllers

import models.PersonSignup
import play.api.mvc._
import services.AddUser

class SignupController extends Controller {

  def errorRedirect = Action {
    Ok(views.html.main("Inavlid Data")(views.html.redirectAfterError()))
  }

}
