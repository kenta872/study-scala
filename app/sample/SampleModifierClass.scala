package sample


/**
 * 修飾子
 */
class SampleModifierClass(x: Int) {
    /**
     * 遅延読み込み
     * 呼ばれたときに初めて計算
     * 以降は結果のみを参照する
     */
    lazy val lazySample: Double = {
        println("calculate")
        x * x * math.Pi
    }

    /**
     * クラス内でのみ参照可能
     *
     * @param message
     */
    private def privateMethod(message: String): Unit = {
        println(message)
    }

    /**
     * 継承先からもアクセス可能
     *
     * @param message
     */
    protected def protectedMethod(message: String): Unit = {
        println(message)
    }

    /**
     * オーバーライド不可
     *
     * @param message
     */
    final def finalMethod(message: String): Unit = {
        println(message)
    }
}
