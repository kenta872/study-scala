package service

object SampleService {
    def formatNames(names: Seq[String]): String = {
        val formattedNames = names.map(_ + " san").mkString("[", ", ", "]")
        s"Hello, $formattedNames"
    }
}