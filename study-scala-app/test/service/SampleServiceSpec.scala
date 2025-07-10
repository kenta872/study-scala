package service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SampleServiceSpec extends AnyFlatSpec with Matchers {

    val sampleService = new SampleService()

    "formatNames" should "format a list of names correctly" in {
        val names = Seq("Alice", "Bob")
        val result = sampleService.formatNames(names)
        result shouldBe "Hello, [Alice san, Bob san]"
    }

    it should "handle an empty list of names" in {
        val names = Seq.empty[String]
        val result = sampleService.formatNames(names)
        result shouldBe "Hello, []"
    }

    it should "handle a list with one name" in {
        val names = Seq("Alice")
        val result = sampleService.formatNames(names)
        result shouldBe "Hello, [Alice san]"
    }

    it should "handle a list with names containing special characters" in {
        val names = Seq("Al!ce", "B@b")
        val result = sampleService.formatNames(names)
        result shouldBe "Hello, [Al!ce san, B@b san]"
    }

}
