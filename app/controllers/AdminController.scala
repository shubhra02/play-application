package controllers

import com.google.inject.Inject
import models.PersonSignup
import play.api.cache.CacheApi
import play.api.mvc.{Action, Controller}
import services.{AddUser, PersonInfo}

import scala.collection.mutable.ListBuffer

class AdminController @Inject()(cache: CacheApi, person: PersonInfo) extends Controller{

  def showManagement = Action { implicit  request =>

    val getAllUsers: ListBuffer[PersonSignup] =  AddUser.listOfPerson
    val users = for {
      user <- getAllUsers
    }yield  person.getPersonData(user.email)

    Ok(views.html.management("Manage Page")(users.flatten.toList))

  }

  def blockUser(email: String) = Action { implicit request =>

    val userData: Option[PersonSignup] = person.getPersonData(email)
    val toBlock = processData(userData)
    val blocked = toBlock.copy(isBlocked = true)
    cache.remove(email)
    cache.set(email, blocked)

    Redirect(routes.AdminController.showManagement())
  }

  def activate(email: String) = Action { implicit request =>
    val userData = person.getPersonData(email)
    val toActivate = processData(userData)
    val active = toActivate.copy(isBlocked = false)
    cache.remove(email)
    cache.set(email, active)

    Redirect(routes.AdminController.showManagement())

  }

  def processData(person: Option[PersonSignup]) = {
    person match {
      case Some(data) => data
    }
  }

}
