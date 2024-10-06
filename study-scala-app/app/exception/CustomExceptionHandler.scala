package exception

import play.api.http.HttpErrorHandler
import play.api.mvc.Results.{BadRequest, InternalServerError, Status, Unauthorized}
import play.api.mvc.{RequestHeader, Result}

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class CustomExceptionHandler @Inject() extends HttpErrorHandler {
    override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
        Future.successful(
            Status(statusCode)("A client error occurred: " + message)
        )
    }

    override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
        Future.successful {
            exception match {
                case _: IllegalArgumentException =>
                    // 400 Bad Request
                    BadRequest("Bad request: " + exception.getMessage)
                case _: UnauthorizedException =>
                    // 401 Unauthorized
                    Unauthorized("Resource not found: " + exception.getMessage)
                case _ =>
                    // 500 Internal Server Error
                    InternalServerError("A server error occurred: " + exception.getMessage)
            }
        }
    }
}
