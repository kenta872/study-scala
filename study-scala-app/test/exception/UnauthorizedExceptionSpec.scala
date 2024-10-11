package exception

import org.scalatestplus.play.PlaySpec

class UnauthorizedExceptionSpec extends PlaySpec {
    "UnauthorizedException" should {
        "return the correct message" in {
            val message = "Unauthorized Access"
            val exception = new UnauthorizedException(message)
            exception.getMessage mustBe message
        }

        "be an instance of RuntimeException" in {
            val exception = new UnauthorizedException("Unauthorized Access")
            exception mustBe a[RuntimeException]
        }
    }
}
