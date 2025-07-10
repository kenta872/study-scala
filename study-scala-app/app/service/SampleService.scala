package service

import javax.inject.{Inject, Singleton}

@Singleton
class SampleService @Inject()() {
    def formatNames(names: Seq[String]): String = {
        val formattedNames = names.map(_ + " san").mkString("[", ", ", "]")
        s"Hello, $formattedNames"
    }
}
