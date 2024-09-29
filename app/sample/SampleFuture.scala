package sample

import java.net.UnknownHostException
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success}

/**
 * 非同期処理
 */
class SampleFuture {

    def futureMethod1(): Unit = {
        val future = Future(HttpTextClient.get("http://scalamaturi.org"))
        future.onComplete {
            case Success(body) =>
                println(body.mkString)
                body.close()
            case Failure(e) => println("エラーが発生" + e.toString)
        }

        future.map(
            s =>
                try s.mkString
                finally s.close
        ).onComplete {
            case Success(body) => println(body)
            case Failure(e) => e.printStackTrace()
        }
    }

    def futureMethod2(): Unit = {
        val urlsFuture: Future[Seq[String]] = getAsync("http://scalamatsuri.org/").flatMap(extractURLAsync)
        urlsFuture.onComplete {
            case Success(urlList) =>
                println("指定したURL" +
                    "には次のURLが含まれていました。:" + urlList.mkString(","))
            case Failure(e) => e.printStackTrace()
        }
    }

    def futureMethod3(): Unit = {
        val future1 = Future((1 to 10000).sum)
        val future2 = Future((1 to 10000).sum)
        for {
            i <- future1
            j <- future2
        } println(i + j)
    }

    def futureMethod4(): Unit = {
        getAsync("http://scalamatsuri.org/").foreach {
            body => println("指定したURLのbody : " + body)
        }
    }

    def futureMethod5(): Unit = {
        getAsync("http://ssssss/").recover {
            case _ => "NotFound?"
        }.foreach {
            body => println("指定したURLのbody : " + body)
        }
    }

    def futureMethod6(): Unit = {
        getAsync("http://2012.scalamatsuri.ong/").recoverWith {
            case _: UnknownHostException =>
                getAsync("http://2013.scalamatsuri.org/")
            case t => Future.failed(t)
        }.foreach {
            body => println("指定したURLのbody : " + body)
        }
    }


    private def getAsync(url: String): Future[String] =
        Future(HttpTextClient.get(url)).map(
            s =>
                try s.mkString
                finally s.close
        )

    private def extractURLAsync(body: String): Future[Seq[String]] = {
        val urlRegix = """https?:\\[\w.:$%?&()=_+-~]+""".r
        Future {
            urlRegix.findAllIn(body).toSeq
        }
    }

    private object HttpTextClient {
        def get(url: String): BufferedSource = Source.fromURL(url)
    }
}
