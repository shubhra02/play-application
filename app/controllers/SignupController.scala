package controllers




import com.google.inject.Inject
import models.PersonSignup
import play.api.mvc._
import services.PersonInfo



class SignupController @Inject()(personObj: PersonInfo) extends Controller {

  def showProfile = Action { implicit request =>
    request.session.get("email").map { person =>

      val data: Option[PersonSignup] = personObj.getPersonData(person)

      Ok(views.html.profile("My profile")(data.toList))
    }.getOrElse {
      Unauthorized("Oops, Connection Terminated!! ")
    }
  }

}
