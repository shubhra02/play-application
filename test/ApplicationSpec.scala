import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }


  "HomeController" should {

    "render the signin page" in {
      val signin = route(app, FakeRequest(GET, "/")).get

      status(signin) mustBe OK
      contentType(signin) mustBe Some("text/html")
      contentAsString(signin) must include ("Welcome to the Website!")
    }

  }

  "SignupController" should {

    "render the profile page" in {
      val profile = route(app, FakeRequest(GET, "/profile")).get

      status(profile) mustBe OK
      contentType(profile) mustBe Some("text/html")
      contentAsString(profile) must include ("Phone Number")

    }
  }

  "CountController" should {

    "return an increasing count" in {
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "0"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "1"
      contentAsString(route(app, FakeRequest(GET, "/count")).get) mustBe "2"
    }

  }

}
