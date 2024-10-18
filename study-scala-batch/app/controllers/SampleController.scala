package controllers

import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class SampleController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

    def sampleBatchController: Action[AnyContent] = Action { _ =>
        Ok("Hello, Batch!")
    }
}
