import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers.{contentAsString, _}

class RoutesSpec extends PlaySpec with OneAppPerTest{

  "signUp" should {

    "redirect to the signup page successfully" in {
      val signup = route(app, FakeRequest(GET, "/signUp")).get
      status(signup) equals 303
    }

  }

  "signupPost" should {

    "redirect to the profile page successfully" in {
      val signup = route(app, FakeRequest(GET, "/signuppost")).get
      status(signup) equals 303
    }

  }

  "signInPost" should {

    "redirect to the profile page successfully after signin" in {
      val signin = route(app, FakeRequest(GET, "/signinpost")).get
      status(signin) equals 303
    }

  }

  "logout" should {

    "redirect to the signin page successfully after logout" in {
      val logoutOfProfile = route(app, FakeRequest(GET, "/logout")).get
      status(logoutOfProfile) equals 303
    }

  }

  "showManagement" should {

    "redirect to the management page successfully for admin" in {
      val management = route(app, FakeRequest(GET, "/manage")).get
      status(management) equals 200
    }
  }

  "blockUser" should {

      "redirect to the management page successfully after blocking" in {
        val block = route(app, FakeRequest(GET, "/manageblock")).get
        status(block) equals 303
      }
  }

  "activate" should {

    "redirect to the management page successfully after activating" in {
      val block = route(app, FakeRequest(GET, "/manageactive")).get
      status(block) equals 303
    }
  }


}
