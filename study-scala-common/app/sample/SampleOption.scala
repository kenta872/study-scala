package sample

import java.io.File


class SampleOption {

    def optionMethod1(): Unit = {
        val files = retrieveListFiles(new File(""))
        if (files.isDefined) {
            println(files)
        }
    }

    /**
     * foreach
     * Optionから値を取得するときはこれを使用する
     */
    def optionMethod2(): Unit = {
        val size = fileSize(new File(""))
        // sizeがNoneの場合例外となるため非推奨
        if (size.isDefined) {
            println(size.get)
        }
        // こっちを使用する(Noneの場合は実行されない)
        size.foreach(println)
    }

    /**
     * map
     */
    def optionMethod3(): Unit = {
        Option(123L).map(_.toString).foreach(println)
    }

    /**
     * flatMap
     */
    def optionMethod4(): Unit = {
        val maybeFile = Option(new File(""))
        val size = maybeFile.flatMap(fileSize)
        size.foreach(println)
    }

    /**
     * getOrElse
     */
    def optionMethod5(): Unit = {
        val list: List[Option[Int]] = List(Option(123), None)
        list.map(getIntOrZero).foreach(println)
    }

    private def retrieveListFiles(directory: File): Option[Array[File]] = Option(directory.listFiles())

    private def fileSize(file: File): Option[Long] = if (file.exists()) Option(file.length()) else None

    private def getIntOrZero(option: Option[Int]): Int = option.getOrElse(0)
}
