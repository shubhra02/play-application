import models.PersonSignup
import play.api.cache.CacheApi
import services.PersonData
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._

import org.mockito.Mockito._

class ServiceSpec extends PlaySpec with MockitoSugar{

  "getPersonData" should {
    "get the person with the specified email Id" in {
      val cache = mock[CacheApi]
      val mockObj = new PersonData(cache)
      when(mockObj.getPersonData("shubhra@gmail.com")) thenReturn Option(PersonSignup("","","shubhra@gmail.com","","","","",false, true))
      mockObj.isData("shubhra@gmail.com") mustBe false

    }
  }

}
