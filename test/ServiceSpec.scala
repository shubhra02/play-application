import models.PersonSignup
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.cache.CacheApi
import services.PersonData


class ServiceSpec extends PlaySpec with MockitoSugar{

  "getPersonData" should {
    "get the person with the specified email Id" in {
      val cache = mock[CacheApi]
      val mockObj = new PersonData(cache)
      cache.set("shubh@gmail.com",PersonSignup(" "," ","shubh@gmail.com"," "," "," "," ",true, false))
      mockObj.getPersonData("shubh@gmail.com") mustBe PersonSignup(" "," ","shubh@gmail.com "," "," "," "," ",true, false)
    }
  }
}
