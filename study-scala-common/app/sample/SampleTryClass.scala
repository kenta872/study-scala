package sample

import scala.util.Try

/**
 * try式
 */
class SampleTryClass {
    def tryMethod1(): Unit = {
        try {
            throw new RuntimeException("例外発生！")
        } catch {
            case e: RuntimeException =>
                println("#######")
                println(e.getMessage)
                println("#######")
            case e: Exception => println(e.getMessage)
        }
    }

    def tryMethod2(): Unit = {
        val successResult = div(10, 3)
        println(successResult)
        val failureResult = div(1, 0)
        println(failureResult)
    }

    /**
     * foreach
     */
    def tryMethod3(): Unit = {
        div(100, 20).foreach(println)
        div(1, 0).foreach(println)
    }

    /**
     * foreach with failed
     */
    def tryMethod4(): Unit = {
        div(10, 3).failed.foreach(println)
        div(1, 0).failed.foreach(println)
    }

    /**
     * map
     */
    def tryMethod5(): Unit = {
        val successResult = div(10, 3).map(_ * 3)
        println(successResult)
        val failureResult = div(1, 0).map(_ * 3)
        print(failureResult)
    }

    /**
     * flatMap
     */
    def tryMethod6(): Unit = {
        div(10, 3).flatMap(i => div(12, i)).foreach(println)
    }

    /**
     * recover
     * Tryの結果を返す
     * Try(2)を返していた場合SUCCESS(Try(2))を返す
     */
    def tryMethod7(): Unit = {
        div(10, 0).recover {
            case _: ArithmeticException => 0
        }
    }

    /**
     * recoverWith
     * Tryオブジェクトを返す
     */
    def tryMethod8(): Unit = {
        div(10, 0).recoverWith {
            case _: ArithmeticException => Try(1 + 1)
        }
    }

    /**
     * getOrElse
     */
    def tryMethod9(): Unit = {
        val successResult = div(10, 3).getOrElse(-1)
        println(successResult)
        val failureResult = div(10, 0).getOrElse(-1)
        println(failureResult)
    }

    private def div(a: Int, b: Int): Try[Int] = Try(a / b)
}