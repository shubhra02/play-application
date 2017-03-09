package services

import java.io.Serializable

import com.google.inject.Inject
import models.PersonSignup
import play.api.cache
import play.api.cache.CacheApi

import scala.collection.mutable
trait PersonInfo {
  def getPersonData(email: String) : Option[PersonSignup]
}
class PersonData @Inject()(cache: CacheApi)extends PersonInfo {

    override def getPersonData(email : String): Option[PersonSignup] = {
        cache.get[PersonSignup](email)
    }

  def isData(email: String) : Boolean = {
    val list = AddUser.listOfPerson
    val exist = list map{
      user => (user.email)
    }
    if(exist.contains(email)) true
    else false

  }

}
