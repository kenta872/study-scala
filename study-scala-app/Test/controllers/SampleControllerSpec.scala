package controllers

import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.libs.json.Json
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._
import sample.SampleConditionClass
import service.UserService

import scala.collection.Seq
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.runtime.{universe, currentMirror => cm}

class SampleControllerSpec extends PlaySpec with Results with MockitoSugar {
    "SampleController#findByName" should {
        "return formatted name" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)

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
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)

            // when
            when(mockUserService.findAll()).thenReturn(Future.successful("1: Alice\n2: Bob\n3: Carol"))

            // do
            val result = controller.findAll().apply(FakeRequest())

            //assert
            status(result) mustBe OK
            contentAsJson(result) mustBe Json.toJson("1: Alice\n2: Bob\n3: Carol")
        }

        "handle errors gracefully" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)

            // when
            when(mockUserService.findAll()).thenReturn(Future.failed(new Exception("Database error")))

            // do
            val result = controller.findAll().apply(FakeRequest())

            // assert
            status(result) mustBe INTERNAL_SERVER_ERROR
            contentAsString(result) mustBe "An error occurred"
        }
    }

    /**
     * privateメソッドのテストを行うことは非推奨とのこと
     * 念の為、サンプルとして記載している
     */
    "SampleController#formatNames" should {
        "format a single name correctly" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)
            val methodSymbol = cm.classSymbol(controller.getClass).toType.decl(universe.TermName("formatNames")).asMethod
            val method = cm.reflect(controller).reflectMethod(methodSymbol)

            // do
            val result = method(Seq("Alice"))

            // assert
            result mustBe "Hello, [Alice san]"
        }
        "format multiple names correctly" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)
            val methodSymbol = cm.classSymbol(controller.getClass).toType.decl(universe.TermName("formatNames")).asMethod
            val method = cm.reflect(controller).reflectMethod(methodSymbol)

            // do
            val result = method(Seq("Alice", "Bob"))

            // assert
            result mustBe "Hello, [Alice san, Bob san]"
        }

        "handle an empty list of names" in {
            // setup
            val mockUserService = mock[UserService]
            val mockSampleConditionClass = mock[SampleConditionClass]
            val controller = new SampleController(mockUserService, mockSampleConditionClass, stubMessagesControllerComponents())(ExecutionContext.global)
            val methodSymbol = cm.classSymbol(controller.getClass).toType.decl(universe.TermName("formatNames")).asMethod
            val method = cm.reflect(controller).reflectMethod(methodSymbol)

            // do
            val result = method(Seq.empty)

            // assert
            result mustBe "Hello, []"
        }
    }
}
