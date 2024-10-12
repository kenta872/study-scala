package service

import model.entity.User
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import repository.UserRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceSpec extends PlaySpec with MockitoSugar {
    "UserService#findAll" should {
        "return all users" in {
            val userRepository = mock[UserRepository]
            val userService = new UserService(userRepository)
            when(userRepository.findAll()).thenReturn(Future(Seq(User(1, "Alice", "test"), User(2, "Bob", "test"))))

            // Futureの結果を取得
            val result = userService.findAll()
            val expected = "[Alice-san(1) ,Bob-san(2) ]"
            result.futureValue mustBe expected
        }
    }
}
