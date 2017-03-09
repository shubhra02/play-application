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
    println(users)
//    Ok(views.html.main("Management")(views.html.management(users.toList)))
    Ok(views.html.management("Manage Page")(users.toList))

  }

  def blockUser(email: String) = Action { implicit request =>

    val toBlock = person.getPersonData(email)
    val blocked = toBlock.copy(isBlocked = true)
    cache.remove(email)
    cache.set(email, blocked)

    Redirect(routes.AdminController.showManagement())
  }

  def activate(email: String) = Action { implicit request =>
    val toActivate = person.getPersonData(email)
    val active = toActivate.copy(isBlocked = false)
    cache.remove(email)
    cache.set(email, active)

    Redirect(routes.AdminController.showManagement())

  }

}
