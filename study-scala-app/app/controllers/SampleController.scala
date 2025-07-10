package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import service.{SampleService, UserService}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class SampleController @Inject()(userService: UserService,
                                 sampleService: SampleService,
                                 cc: MessagesControllerComponents)
                                (implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

    def findByName(name: String): Action[AnyContent] = Action { _ =>
        Ok(sampleService.formatNames(Seq(name)))
    }

    def findAll: Action[AnyContent] = Action.async { _ =>
        userService.findAll().map { users =>
            Ok(Json.toJson(users))
        }.recover {
            case _: Exception =>
                InternalServerError("An error occurred")
        }
    }
}
