package sample

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SampleLoopClassSpec extends AnyFlatSpec with Matchers {
    "loopMethod1" should "print numbers from 0 to 2" in {
        val sample = new SampleLoopClass
        sample.loopMethod1()
    }
}
