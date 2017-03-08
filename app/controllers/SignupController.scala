package controllers




import com.google.inject.Inject
import models.PersonSignup
import play.api.mvc._
import services.PersonInfo



class SignupController @Inject()(personObj: PersonInfo) extends Controller {

  def showProfile(email: String) = Action { implicit request =>
    request.session.get("email").map { person =>

      val data = personObj.getPersonData(person)
      if(data.isAdmin) {
        Redirect(routes.HomeController.manage)
      }

      Ok(views.html.profile(data))
    }.getOrElse {
      Unauthorized("Oops, Connection Terminated!! ")
    }
  }

}
