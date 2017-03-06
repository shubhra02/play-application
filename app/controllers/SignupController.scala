package controllers


import javax.inject.Inject

import play.api.mvc._
import services.PersonData



class SignupController @Inject() extends Controller {

  def showProfile(username: String) = Action { implicit request =>
    request.session.get("username").map { person =>
      val personObj = new PersonData
      val data = personObj.getPersonData(person)

      Ok(views.html.profile(data))
    }.getOrElse {
      Unauthorized("Oops, Connection Terminated!! ")
    }
  }

}
