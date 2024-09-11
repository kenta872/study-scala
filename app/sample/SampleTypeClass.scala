package sample


/**
 * 型
 */
class SampleTypeClass {
    /**
     * 数値型
     */
    def numSample(): Unit = {
        val intData: Int = 1
        println("Int: " + intData)
        val longData: Long = 1L
        println("Long: " + longData)
        val byteData: Byte = 127
        println("Byte: " + byteData)
        val shortData: Short = 32767
        println("Short: " + shortData)
        val doubleData: Double = 1.0
        println("Double: " + doubleData)
        val floatData: Float = 1.0F
        println("Float: " + floatData)
    }

    /**
     * 文字列型
     */
    def strSample(): Unit = {
        val charData: Char = 'a'
        println("Char: " + charData)
        val strData: String = "str"
        println("String: " + strData)
    }

    /**
     * Boolean型
     */
    def booleanSample(): Unit = {
        val booleanData: Boolean = true
        println("Boolean: " + booleanData)
    }

    /**
     * その他
     */
    def otherSample(): Unit = {
        val nullData: Null = null
        println("Null: " + nullData)
    }

    /**
     * taple型
     */
    def tapleSample(): Unit = {
        val taple1: (Int, Int) = (10, 20)
        println(taple1._1)
        val taple2: (Int, String) = (1, "One")
        println(taple2._2)
    }

    /**
     * コレクション(Seq)
     */
    def seqSample(): Unit = {
        val seq = Seq("A", "BB", "C", "AA")
        val intSeq = Seq(1, 2, 3)
        val tapleSeq = Seq("hello" -> 1, "world" -> 2)
        println(seq)
        println(seq(1))
        println(seq.head)
        println(seq.last)
        println(seq.contains("A"))

        // 先頭2要素を取り出す
        val takeSeq = seq.take(2)
        println(takeSeq)

        // 末尾2要素を取り出す
        val takeRightSeq = seq.takeRight(2)
        println(takeRightSeq)

        // 先頭からfalseになるまで取り出す
        val takeWhileSeq = seq.takeWhile(_.length < 2)
        println(takeWhileSeq)

        // 先頭2要素を削除
        val dropSeq = seq.drop(2)
        println(dropSeq)

        // 末尾2要素を削除
        val dropRightSeq = seq.dropRight(2)
        println(dropRightSeq)

        // 先頭からfalseになるまで削除
        val dropWhileSeq = seq.dropWhile(_.length < 2)
        println(dropWhileSeq)

        // すべての要素をフィルタ
        val filteredSeq = seq.filter(_.length < 2)
        println(filteredSeq)

        // 昇順ソート
        val sortedSeq = seq.sorted
        println(sortedSeq)

        // 降順ソート
        val reveredSeq = seq.reverse
        println(reveredSeq)

        // 畳み込み
        val foldLeft = intSeq.foldLeft(0)(_ + _)
        println(foldLeft)
        val foldRight = intSeq.foldRight(0)(_ + _)
        println(foldRight)

        // 変換
        val convertedSet = seq.toSet
        println(convertedSet)
        val convertedMap = tapleSeq.toMap
        println(convertedMap)

        // 不変
        val immutableSeq = scala.collection.immutable.Seq(1, 2, 3)
        //  immutableSeq(0) = 10 できない
        println(immutableSeq)
        val updateImmutableSeq = 10 +: immutableSeq :+ 99
        println(updateImmutableSeq)

        // 可変
        val mutableSeq = scala.collection.mutable.Seq(1, 2, 3)
        mutableSeq(0) = 10
        println(mutableSeq)
        val updateMutableSeq = 10 +: mutableSeq :+ 99
        println(updateMutableSeq)

        val sumSeq = updateMutableSeq ++ updateMutableSeq
        println(sumSeq)

    }

    /**
     * コレクション(Set)
     */
    def setSample(): Unit = {
        val set = Set("A", "C", "B", "A")
        println(set.contains("A"))
        println(set("A"))

        // 不変
        val immutableSet = scala.collection.immutable.Set("A", "B", "C")
        val updatedImmutableSet = "S" + immutableSet + "E"
        println(updatedImmutableSet)

        // 可変
        val mutableSet = scala.collection.mutable.Set("A", "B", "C")
        mutableSet -= "B"
        mutableSet += "DD"
        val updatedMutableSet = "SS" + mutableSet + "EE"
        println(updatedMutableSet)

        // 結合
        val sumSet = updatedMutableSet ++ updatedMutableSet
        println(sumSet)
    }

    /**
     * コレクション(<ap>)
     */
    def mapSample(): Unit = {
        val map = Map("A" -> 10, "C" -> 20, "B" -> 30)
        println(map("B"))
        println(map.contains("A"))
        map.get("A").foreach(println)
        println(map.getOrElse("D", 99))
        map + ("D" -> 40)
        map ++ Map("E" -> 50, "F" -> 60)
        println(map)
    }
}