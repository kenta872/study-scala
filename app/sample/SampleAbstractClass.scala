package sample

/**
 * 継承元クラス
 * インスタンス生成することができないクラス
 */
abstract class SampleAbstractClass {
    def draw(): Unit = {
        println("abstract target")
    }
}
