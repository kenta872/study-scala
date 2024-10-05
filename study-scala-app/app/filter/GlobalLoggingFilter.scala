package filter

import org.apache.pekko.stream.Materializer
import play.api.Logger
import play.api.mvc.{EssentialAction, EssentialFilter}

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.ExecutionContext

class GlobalLoggingFilter @Inject()()(implicit val mat: Materializer, ec: ExecutionContext) extends EssentialFilter {

    private val logger = Logger(this.getClass)

    override def apply(next: EssentialAction): EssentialAction = EssentialAction { request =>
        val uuid = UUID.randomUUID()
        logger.info(s"Before request - requestId: ${uuid}, method:${request.method}, uri:${request.uri}")
        val startTime = System.currentTimeMillis
        next(request).map { result =>
            val endTime = System.currentTimeMillis
            val requestTime = endTime - startTime
            logger.info(s"After request - requestId: ${uuid}, processing time: ${requestTime}ms, state: ${result.header.status}")
            result
        }
    }
}
