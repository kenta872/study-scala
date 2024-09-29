package sample


/**
 * 【ループ】
 */
class SampleLoopClass {
    /**
     * while
     */
    def loopMethod1(): Unit = {
        var i = 0
        while (i < 3) {
            println(i)
            i += 1
        }
    }

    /**
     * filter
     */
    def loopMethod2(): Unit = {
        val filtered1 = Seq(1, 2, 3).filter(i => i > 1)
        println(filtered1)
        val filtered2 = Seq(1, 2, 3).filter(_ > 2)
        println(filtered2)
    }

    /**
     * map
     */
    def loopMethod3(): Unit = {
        val map1 = Seq(1, 2, 3).map(i => i * 2)
        println(map1)
        val map2 = Seq(1, 2, 3).map(_ * 2)
        println(map2)
        val map3 = Seq(1, 2, 3).map { i =>
            println(i)
            i * 2
        }
        println(map3)
    }

    /**
     * flatMap
     */
    def loopMethod4(): Unit = {
        Seq(Seq(1, 2), Seq(3, 4)).flatMap { s =>
            println(s)
            s
        }
    }

    /**
     * flatten
     */
    def loopMethod5(): Unit = {
        val flat = Seq(Seq(1, 2), Seq(3, 4)).flatten
        println(flat)
    }

    /**
     * for caseA
     */
    def loopMethod6(): Unit = {
        for (x <- 1 to 3; y <- 1 until 3) {
            println("x=" + x + "y=" + y)
        }
    }

    /**
     * for caseB(ガード)
     */
    def loopMethod7(): Unit = {
        for (x <- 1 to 3; y <- 1 until 3 if x != y) {
            println("x=" + x + "y=" + y)
        }
    }

    /**
     * for caseC
     */
    def loopMethod8(): Unit = {
        for (e <- List(1, 2, 3)) println(e)
    }

    /**
     * for caseD
     */
    def loopMethod9(): Unit = {
        val result: Seq[Int] = for {
            i <- 1 to 3
            j <- 2 to 4
            k <- 3 to 5
            re = i * j * k
            if re % 3 == 0
        } yield re
        println(result)
    }
}