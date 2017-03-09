package controllers




import com.google.inject.Inject
import models.PersonSignup
import play.api.mvc._
import services.PersonInfo



class SignupController @Inject()(personObj: PersonInfo) extends Controller {

  def showProfile = Action { implicit request =>
    request.session.get("email").map { person =>

      val data = personObj.getPersonData(person)

      Ok((views.html.profile("My profile")(data)))
    }.getOrElse {
      Unauthorized("Oops, Connection Terminated!! ")
    }
  }

}
