import org.scalatest.mock.MockitoSugar
import play.api.mvc.Flash
import org.scalatestplus.play._
import play.api.test.Helpers.{contentAsString, _}

class ViewsSpec extends PlaySpec with MockitoSugar{

  "" in new App{
    val flashMock = mock[Flash]
    val html = views.html.signin()(flashMock)
    contentAsString(html) must include ("")
  }

}
