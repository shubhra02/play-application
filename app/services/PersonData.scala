package services

import java.io.Serializable

import com.google.inject.Inject
import models.PersonSignup
import play.api.cache
import play.api.cache.CacheApi

import scala.collection.mutable
trait PersonInfo {
  def getPersonData(email: String):PersonSignup
}
class PersonData @Inject()(cache: CacheApi)extends PersonInfo {
    override def getPersonData(email : String) = {

     val personData = cache.get[models.PersonSignup](email)
      personData match {
        case Some(data) => data
        case _ => PersonSignup(" "," "," "," "," "," "," ",false,true)
      }
     /*val personData = AddUser.listOfPerson.filter(_.email == email)*/
   }

}
