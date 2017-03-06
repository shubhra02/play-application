package controllers

import javax.inject.Inject

import models.PersonSignup
import services.AddUser._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.AddUser

class RegisterController @Inject() extends Controller {

  def signUp = Action { implicit  request =>
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
      "phone" -> number
    )(PersonSignup.apply)(PersonSignup.unapply)
  )

  def personPost = Action { implicit request =>
    signupData.bindFromRequest.fold(
      formWithErrors => {
          Redirect(routes.RegisterController.signUp).flashing("meassage" -> "Invalid Data, Try again")
      },
      formData => {
        println(formData)
        val newPerson = AddUser.listOfPerson
        val dataBind = signupData.bindFromRequest.get
          if (formData.password == formData.rePassword) {

            if (formData.phone.toString.length == 10) {
              AddUser.listOfPerson.append(formData)
              Redirect(routes.SignupController.showProfile(formData.email)).withSession("newuser" -> formData.email).flashing("message" -> "Registration Successful")
            }
            else {
              Redirect(routes.HomeController.index()).flashing("invalidphone" -> "Phone number invalid")
            }
          }
          else
            Redirect(routes.HomeController.index()).flashing("matcherror" -> "Pasword dosent Match")
        }
    )
  }
}
