package sample


/**
 * 【match】
 */
class SampleMatchClass {
    def matchMethod1(): Unit = {
        val matchN = 3
        matchN match {
            case 1 => println("One")
            case 2 => println("Two")
            case 3 => println("Three")
            case _ => println("Other")
        }
    }

    def matchMethod2(): Unit = {
        val maybeNum: Option[Int] = Some(123)
        val number2: Int = maybeNum match {
            case Some(num) => num
            case None => 0
        }
        println(number2)
    }

    /**
     * ガード
     */
    def matchMethod3(): Unit = {
        val maybeNone: Option[Int] = None
        val number3: Int = maybeNone match {
            case Some(num) if num < 0 => 0
            case Some(num) => num
            case None => 0
        }
        println(number3)
    }

    /**
     * Collectionマッチ
     */
    def matchMethod4(): Unit = {
        val sampleList4 = List(1, 2, 3, 4, 5)
        sampleList4 match {
            case List(1, 2, 3) => println(1, 2, 3)
            case List(1, 2, 3, 4) => println(1, 2, 3, 4)
            case List(1, 3, 2, 4, 5) => println(1, 3, 2, 4, 5)
            case List(a, b, c, d, e) => println(a, b, c, d, e)
            case _ => println("???")
        }
    }

    /**
     * リストの最後から2番目の要素を取り出す
     *
     * @param list 取り出す対象となるリスト
     * @tparam A
     */
    def matchMethod5[A](list: List[A]): A = list match {
        case x :: _ :: Nil => x
        case x :: xs => matchMethod5(xs)
        case _ => sys.error("list should have two elements at least")
    }

    /**
     * 再帰関数を利用したCollection反転メソッド
     *
     * @param list   反転対象リスト
     * @param result 反転結果一時蓄積リスト
     */
    def reverse[A](list: List[A], result: List[A]): List[A] = list match {
        case x :: xs => reverse(xs, x :: result)
        case Nil => result
    }
}
