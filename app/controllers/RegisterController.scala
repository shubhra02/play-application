package controllers



import com.google.inject.Inject
import models.PersonSignup
import play.api.cache.CacheApi
import services.AddUser._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.{AddUser, MD5, PersonInfo}

class RegisterController @Inject()(personObj: PersonInfo) extends Controller {

  def signUp = Action { implicit request =>
    Ok(views.html.signup())
  }

  val signupData  : Form[PersonSignup] = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "rePassword" -> nonEmptyText,
      "company" -> text,
      "phone" -> text,
      "isAdmin" -> boolean
        )(PersonSignup.apply)(PersonSignup.unapply)
  )

  def signupPost = Action { implicit request =>
    signupData.bindFromRequest.fold(
      formWithErrors => {

          Redirect(routes.RegisterController.signUp).flashing("meassage" -> "Invalid Data, Try again")
      },
      formData => {

        val newPerson = AddUser.listOfPerson
        if (formData.password == formData.rePassword) {

          if (formData.phone.toString.length == 10) {

            val encrypted = formData.copy(password = MD5.hash(formData.password))
            personObj.setPersonData(formData.firstName, formData.lastName, formData.email, formData.password, formData.rePassword, formData.company, formData.phone, formData.isAdmin)

            Redirect(routes.SignupController.showProfile(formData.email)).withSession("email" -> formData.email).flashing("message" -> "Registration Successful")
          }
          else {

            Redirect(routes.HomeController.index()).flashing("invalidphone" -> "Phone number invalid")
          }
        }
        else {

          Redirect(routes.HomeController.index()).flashing("matcherror" -> "Pasword dosent Match")
        }
      }
    )
  }
}
