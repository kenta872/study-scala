package sample

class SampleClass(val name: String, sampleArg: String) extends SampleAbstractClass with SampleTrait {
    override def draw(): Unit = {
        println("override draw method! args:" + sampleArg)
    }

    def unary_! : String = "!" + name
}
