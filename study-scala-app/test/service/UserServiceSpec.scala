package service

import model.entity.User
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.db.slick.DatabaseConfigProvider
import repository.UserRepository
import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.MySQLProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserServiceSpec extends PlaySpec with MockitoSugar {
    "UserService#findAll" should {
        "return empty string when no users are found" in {
            // setup
            val testData = Seq.empty[User]
            val mockDbConfigProvider = mock[DatabaseConfigProvider]
            val mockDbConfig = mock[DatabaseConfig[MySQLProfile]]
            val mockDb = mock[mockDbConfig.profile.backend.Database]
            val mockUserRepository = mock[UserRepository]

            // when
            when(mockDbConfigProvider.get[MySQLProfile]).thenReturn(mockDbConfig)
            when(mockDbConfig.db).thenReturn(mockDb)
            when(mockDb.run(DBIO.successful(testData))).thenReturn(Future.successful(testData))
            when(mockUserRepository.findAll()).thenReturn(DBIO.successful(testData))

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

            // when
            when(mockDbConfigProvider.get[MySQLProfile]).thenReturn(mockDbConfig)
            when(mockDbConfig.db).thenReturn(mockDb)
            when(mockDb.run(DBIO.successful(testData))).thenReturn(Future.successful(testData))
            when(mockUserRepository.findAll()).thenReturn(DBIO.successful(testData))

            // do
            val userService = new UserService(mockUserRepository, mockDbConfigProvider)
            val result = userService.findAll().futureValue
            result mustBe "ID: 1, Name: testUserA\nID: 2, Name: testUserB"
        }
    }
}
