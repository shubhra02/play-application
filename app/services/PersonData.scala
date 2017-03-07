package services

import com.google.inject.Inject
import models.PersonSignup
import play.api.cache
import play.api.cache.CacheApi

import scala.collection.mutable
trait PersonInfo {
  def getPersonData(email: String):PersonSignup
  def setPersonData(firstName: String, lastName: String, email: String, password: String, rePassword: String, company: String, phone: String):PersonSignup
}
class PersonData @Inject()(cache: CacheApi)extends PersonInfo {
   override def getPersonData(email : String) = {

     val personData = cache.get[PersonSignup](email)
     /*val personData = AddUser.listOfPerson.filter(_.email == email)*/
     personData
   }
  override def setPersonData(firstName: String, lastName: String, email: String, password: String, rePassword: String, company: String, phone: String):PersonSignup = {
    cache.set(firstName, lastName, email, password, rePassword, company, phone)
  }

}
