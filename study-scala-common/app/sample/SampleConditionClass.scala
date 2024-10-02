package sample

/**
 * 【条件分岐】
 */
class SampleConditionClass {
    def conditionMethod1(): Unit = {
        val weight = 100
        var message: String = null
        if (weight <= 100) {
            message = "OK"
        } else {
            message = "over"
        }
        println(message)
    }

    def conditionMethod2(): Unit = {
        val weight = 100
        val message = if (weight <= 100) {
            "OK"
        } else {
            "over"
        }
        println(message)
    }

    def conditionMethod3(): Unit = {
        val weight = 100
        val message = if (weight <= 100) "OK" else "over"
        println(message)
    }
}
