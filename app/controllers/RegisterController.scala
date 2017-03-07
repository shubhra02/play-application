package controllers

import javax.inject.Inject

import models.PersonSignup
import services.AddUser._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.AddUser

class RegisterController @Inject() extends Controller {

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
      "phone" -> text
    )(PersonSignup.apply)(PersonSignup.unapply)
  )

  def signupPost = Action { implicit request =>
    signupData.bindFromRequest.fold(
      formWithErrors => {
         println(formWithErrors)
          Redirect(routes.RegisterController.signUp).flashing("meassage" -> "Invalid Data, Try again")
      },
      (formData: PersonSignup) => {
        println(formData)
        val newPerson = AddUser.listOfPerson
        if (formData.password == formData.rePassword) {
          if (formData.phone.toString.length == 10) {
            println("Yes")
            val list = AddUser.listOfPerson.append(formData)
            println("okay")
            Redirect(routes.SignupController.showProfile(formData.email)).withSession("email" -> formData.email).flashing("message" -> "Registration Successful")
          }
          else {
            println("not ok1")
            Redirect(routes.HomeController.index()).flashing("invalidphone" -> "Phone number invalid")
          }
        }
        else {
          println("not ok2")
          Redirect(routes.HomeController.index()).flashing("matcherror" -> "Pasword dosent Match")
        }
      }
    )
  }
}
