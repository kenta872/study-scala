package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import sample.SampleConditionClass
import service.UserService

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class SampleController @Inject()(userService: UserService,
                                 sampleConditionClass: SampleConditionClass,
                                 cc: MessagesControllerComponents)
                                (implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {
    def findByName(name: String): Action[AnyContent] = Action { _ =>
        sampleConditionClass.conditionMethod1()
        Ok(formatNames(Seq(name)))
    }

    def findAll: Action[AnyContent] = Action.async { _ =>
        userService.findAll().map { result =>
            Ok(Json.toJson(result))
        }.recover {
            case _: Exception =>
                InternalServerError("An error occurred")
        }
    }

    private def formatNames(names: Seq[String]): String = {
        val formattedNames = names.map(_ + " san").mkString("[", ", ", "]")
        s"Hello, $formattedNames"
    }
}
