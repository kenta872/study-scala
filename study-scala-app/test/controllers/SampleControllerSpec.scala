package controllers

import model.entity.User
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import service.{SampleService, UserService}

import scala.concurrent.{ExecutionContext, Future}

class SampleControllerSpec extends PlaySpec with MockitoSugar {

    "SampleController#findByName" should {
        "return formatted name" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleService = mock[SampleService]
            val controller = new SampleController(mockUserService, mockSampleService, stubMessagesControllerComponents())(ExecutionContext.global)

            // when
            when(mockSampleService.formatNames(Seq("Alice"))).thenReturn("Hello, [Alice san]")

            // do
            val result = controller.findByName("Alice").apply(FakeRequest())

            // assert
            status(result) mustBe OK
            contentAsString(result) mustBe "Hello, [Alice san]"
        }
    }

    "SampleController#findAll" should {
        "return all users as JSON" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleService = mock[SampleService]
            val users = Seq(User(1, "Alice", "pass"), User(2, "Bob", "pass"))
            val controller = new SampleController(mockUserService, mockSampleService, stubMessagesControllerComponents())(ExecutionContext.global)

            // when
            when(mockUserService.findAll()).thenReturn(Future.successful(users))

            // do
            val result = controller.findAll().apply(FakeRequest())

            //assert
            status(result) mustBe OK
            contentType(result) mustBe Some("application/json")
            contentAsJson(result) mustBe Json.toJson(users)
        }

        "handle errors gracefully" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleService = mock[SampleService]
            val controller = new SampleController(mockUserService, mockSampleService, stubMessagesControllerComponents())(ExecutionContext.global)

            // when
            when(mockUserService.findAll()).thenReturn(Future.failed(new Exception("Database error")))

            // do
            val result = controller.findAll().apply(FakeRequest())

            // assert
            status(result) mustBe INTERNAL_SERVER_ERROR
            contentAsString(result) mustBe "An error occurred"
        }
    }
}

