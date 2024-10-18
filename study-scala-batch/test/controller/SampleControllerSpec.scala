package controller

import controllers.SampleController
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.http.Status.OK
import play.api.test.FakeRequest
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout, status, stubMessagesControllerComponents}

class SampleControllerSpec extends PlaySpec with MockitoSugar {
    "SampleController" should {
        "sampleBatchController" should {
            "return Ok" in {
                // setup
                val controller = new SampleController(stubMessagesControllerComponents())

                // do
                val result = controller.sampleBatchController().apply(FakeRequest())

                // assert
                status(result) mustBe OK
                contentAsString(result) mustBe "Hello, Batch!"
            }
        }
    }

}
