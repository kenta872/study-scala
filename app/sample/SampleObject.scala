package sample

object SampleObject extends App {
    println("########## 関数オブジェクト - start ##########")
    private val sampleFunctionClass: SampleFunctionClass = new SampleFunctionClass
    sampleFunctionClass.functionMethod1()
    println("########## 関数オブジェクト - end ##########")


    println("########## 条件分岐 - start ##########")
    private val sampleConditionClass: SampleConditionClass = new SampleConditionClass
    sampleConditionClass.conditionMethod1()
    sampleConditionClass.conditionMethod2()
    sampleConditionClass.conditionMethod3()
    println("########## 条件分岐 - end ##########")


    println("########## match - start ##########")
    private val sampleMatchClass: SampleMatchClass = new SampleMatchClass
    sampleMatchClass.matchMethod1()
    sampleMatchClass.matchMethod2()
    sampleMatchClass.matchMethod3()
    sampleMatchClass.matchMethod4()
    println(sampleMatchClass.reverse(List(1, 2, 3), Nil))
    println(sampleMatchClass.matchMethod5(List(1, 2, 3)))
    println("########## match - end ##########")


    println("########## ループ - start ##########")
    private val sampleLoopClass: SampleLoopClass = new SampleLoopClass
    sampleLoopClass.loopMethod1()
    sampleLoopClass.loopMethod2()
    sampleLoopClass.loopMethod3()
    sampleLoopClass.loopMethod4()
    sampleLoopClass.loopMethod5()
    sampleLoopClass.loopMethod6()
    sampleLoopClass.loopMethod7()
    sampleLoopClass.loopMethod8()
    sampleLoopClass.loopMethod9()
    println("########## ループ - end ##########")


    println("########## 型 - start ##########")
    private val sampleTypeClass: SampleTypeClass = new SampleTypeClass
    sampleTypeClass.numSample()
    sampleTypeClass.strSample()
    sampleTypeClass.booleanSample()
    sampleTypeClass.otherSample()
    sampleTypeClass.tapleSample()
    sampleTypeClass.seqSample()
    sampleTypeClass.setSample()
    sampleTypeClass.mapSample()
    println("########## 型 - end ##########")


    println("########## try式 - start ##########")
    private val sampleTryClass: SampleTryClass = new SampleTryClass
    sampleTryClass.tryMethod1()
    sampleTryClass.tryMethod2()
    sampleTryClass.tryMethod3()
    sampleTryClass.tryMethod4()
    sampleTryClass.tryMethod5()
    sampleTryClass.tryMethod6()
    sampleTryClass.tryMethod7()
    sampleTryClass.tryMethod8()
    sampleTryClass.tryMethod9()
    println("########## try式 - end ##########")


    println("########## 修飾子 - start ##########")
    private val sampleModifierClass: SampleModifierClass = new SampleModifierClass(4)
    sampleModifierClass.lazySample // 1回目(こちらはメッセージも表示される)
    sampleModifierClass.lazySample // 2回目(こちらは値のみ参照される)
    println("########## 修飾子 - end ##########")

    println("########## Option - start ##########")
    private val sampleOption: SampleOption = new SampleOption
    sampleOption.optionMethod1()
    sampleOption.optionMethod2()
    sampleOption.optionMethod3()
    sampleOption.optionMethod4()
    sampleOption.optionMethod5()
    println("########## Option - end ##########")


    println("########## 非同期処理 - start ##########")
    private val sampleFuture: SampleFuture = new SampleFuture
    sampleFuture.futureMethod1()
    sampleFuture.futureMethod2()
    sampleFuture.futureMethod3()
    sampleFuture.futureMethod4()
    sampleFuture.futureMethod5()
    sampleFuture.futureMethod6()
    println("########## 非同期処理 - end ##########")

    /**
     * 【クラス・トレイト】
     */
    val sampleClass: SampleClass = new SampleClass("sampleMethod", "sampleArg")
    sampleClass.draw()
    println(sampleClass.name)

    /**
     * 【特別メソッド】
     */
    // パターン1(apply)
    val specialResult: Int = SpecialObject(1, 2)
    println(specialResult)
    // パターン2(unary)
    val sampleClass2: SampleClass = new SampleClass("sampleClass2", "sampleArg2")
    println(!sampleClass2)

    /**
     * 【caseクラス】
     */
    val sampleCase: SampleCaseClass = SampleCaseClass("sampleArg1", "sampleArg2")
    sampleCase match {
        case SampleCaseClass("sampleArg1", "sampleArg2") => println("Hit!")
        case _ => println("Not Hit!")
    }

    /**
     * 無名クラス
     */
    new SampleClass("sampleClass", "sampleArg") {
        override def draw(): Unit = {
            println("re override!")
        }
    }
}
