package exception

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.http.Status.{BAD_REQUEST, INTERNAL_SERVER_ERROR, UNAUTHORIZED}
import play.api.mvc.RequestHeader
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout, status}

class CustomExceptionHandlerSpec extends PlaySpec with MockitoSugar {
    "CustomExceptionHandler#onClientError" should {
        "return the correct status and message for a client error" in {
            val handler = new CustomExceptionHandler()
            val request = mock[RequestHeader]
            val errorMessage = "Bad Request Message"
            val result = handler.onClientError(request, BAD_REQUEST, errorMessage)

            status(result) mustBe BAD_REQUEST
            contentAsString(result) must include("A client error occurred: %s".format(errorMessage))
        }
    }

    "CustomExceptionHandler#onServerError" should {
        "return BadRequest for IllegalArgumentException" in {
            val handler = new CustomExceptionHandler()
            val request = mock[RequestHeader]
            val errorMessage = "Illegal Argument"
            val exception = new IllegalArgumentException(errorMessage)

            val result = handler.onServerError(request, exception)

            status(result) mustBe BAD_REQUEST
            contentAsString(result) must include("Bad request: %s".format(errorMessage))
        }
        "return Unauthorized for UnauthorizedException" in {
            val handler = new CustomExceptionHandler()
            val request = mock[RequestHeader]
            val errorMessage = "Unauthorized Access"
            val exception = new UnauthorizedException(errorMessage)

            val result = handler.onServerError(request, exception)

            status(result) mustBe UNAUTHORIZED
            contentAsString(result) must include("Resource not found: %s".format(errorMessage))
        }

        "return InternalServerError for other exceptions" in {
            val handler = new CustomExceptionHandler()
            val request = mock[RequestHeader]
            val exception = new Exception("General Error")

            val result = handler.onServerError(request, exception)

            status(result) mustBe INTERNAL_SERVER_ERROR
            contentAsString(result) must include("A server error occurred: General Error")
        }
    }
}
