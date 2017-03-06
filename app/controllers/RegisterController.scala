package controllers

import javax.inject.Inject

import models.PersonSignup
import services.AddUser._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import services.AddUser

class RegisterController @Inject() extends Controller {

  def signUp = Action {
    Ok(views.html.main("SignUp")(views.html.signup()))
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
          Redirect(routes.SignupController.errorRedirect)
      },
      formData => {
        println(formData)

        if(!AddUser.listOfPerson.contains(formData.email)){
          if(formData.password == formData.rePassword){
            if (formData.phone.toString.length == 10) {
             val newUser = AddUser.listOfPerson.append(formData)
            }
          }

        }
        Redirect(routes.HomeController.index)

      }
    )
  }
}
