import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

class ControllersSpec extends PlaySpec with OneAppPerTest{
  "HomeController" should {

    "render the signin page in the start" in {
      val signin = route(app, FakeRequest(GET, "/")).get

      status(signin) mustBe OK
      contentType(signin) mustBe Some("text/html")
      contentAsString(signin) must include ("Welcome to the Website!")
    }

  }

  "HomeController" should {

    "render the signin page after logout" in {
      val logout = route(app, FakeRequest(GET, "/logout")).get

      status(logout) mustBe OK
      contentType(logout) mustBe Some("text/html")
      contentAsString(logout) must include ("Username/Email")
    }

  }

  "RegisterController" should {

    "render the signup page in error case" in {
      val signin = route(app, FakeRequest(GET, "/signup")).get

      status(signin) mustBe OK
      contentType(signin) mustBe Some("text/html")
      contentAsString(signin) must include ("Register")
    }

  }

  "SignupController" should {

    "render the profile page" in {
      val profile = route(app, FakeRequest(GET, "/profile")).get

      status(profile) mustBe 401
      contentType(profile) mustBe Some("text/plain")

    }
  }

}
