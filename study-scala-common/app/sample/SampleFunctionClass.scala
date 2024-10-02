package sample

/**
 * 【関数オブジェクト】
 */
class SampleFunctionClass {
    def functionMethod1(): Unit = {
        val isAlphamericF = (str: String) => str.matches("[a-zA-Z0-9]\\s+")
        val seq: Seq[String] = Seq("testA", "testB", "$$$$")
        seq.foreach(str => println(s"$str: ${isAlphamericF(str)}"))
    }

}
