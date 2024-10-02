package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}
import sample.SampleConditionClass

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class SampleController @Inject()(sampleConditionClass: SampleConditionClass,
                                 cc: MessagesControllerComponents)
                                (implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

    def sampleBatchController: Action[AnyContent] = Action { _ =>
        sampleConditionClass.conditionMethod1()
        Ok("Hello, Batch!")
    }
}
