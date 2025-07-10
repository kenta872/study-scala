package service

import model.entity.User
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.db.slick.DatabaseConfigProvider
import repository.UserRepository
import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.MySQLProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceSpec extends PlaySpec with MockitoSugar with ScalaFutures {
    "UserService#findAll" should {
        "return empty sequence when no users are found" in {
            // setup
            val testData = Seq.empty[User]
            val mockDbConfigProvider = mock[DatabaseConfigProvider]
            val mockDbConfig = mock[DatabaseConfig[MySQLProfile]]
            val mockDb = mock[mockDbConfig.profile.backend.Database]
            val mockUserRepository = mock[UserRepository]
            val findAllAction = DBIO.successful(testData)

            // when
            when(mockDbConfigProvider.get[MySQLProfile]).thenReturn(mockDbConfig)
            when(mockDbConfig.db).thenReturn(mockDb)
            when(mockUserRepository.findAll()).thenReturn(findAllAction)
            when(mockDb.run(findAllAction)).thenReturn(Future.successful(testData))

            // do
            val userService = new UserService(mockUserRepository, mockDbConfigProvider)
            val result = userService.findAll().futureValue
            result mustBe empty
        }

        "return all users" in {
            // setup
            val testData = Seq(
                User(1, "testUserA", "testPasswordA"),
                User(2, "testUserB", "testPasswordB")
            )
            val mockDbConfigProvider = mock[DatabaseConfigProvider]
            val mockDbConfig = mock[DatabaseConfig[MySQLProfile]]
            val mockDb = mock[mockDbConfig.profile.backend.Database]
            val mockUserRepository = mock[UserRepository]
            val findAllAction = DBIO.successful(testData)

            // when
            when(mockDbConfigProvider.get[MySQLProfile]).thenReturn(mockDbConfig)
            when(mockDbConfig.db).thenReturn(mockDb)
            when(mockUserRepository.findAll()).thenReturn(findAllAction)
            when(mockDb.run(findAllAction)).thenReturn(Future.successful(testData))

            // do
            val userService = new UserService(mockUserRepository, mockDbConfigProvider)
            val result = userService.findAll().futureValue
            result mustBe testData
        }

        "recover from exception" in {
            // setup
            val errorMessage = "test error"
            val mockDbConfigProvider = mock[DatabaseConfigProvider]
            val mockDbConfig = mock[DatabaseConfig[MySQLProfile]]
            val mockDb = mock[mockDbConfig.profile.backend.Database]
            val mockUserRepository = mock[UserRepository]
            val findAllAction = DBIO.successful(Seq.empty[User]) // Action doesn't matter much here

            // when
            when(mockDbConfigProvider.get[MySQLProfile]).thenReturn(mockDbConfig)
            when(mockDbConfig.db).thenReturn(mockDb)
            when(mockUserRepository.findAll()).thenReturn(findAllAction)
            when(mockDb.run(findAllAction)).thenReturn(Future.failed(new Exception(errorMessage)))

            // do
            val userService = new UserService(mockUserRepository, mockDbConfigProvider)
            whenReady(userService.findAll().failed) { e =>
                e mustBe a[Exception]
                e.getMessage mustBe errorMessage
            }
        }
    }
}
