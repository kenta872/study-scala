package repository

import model.entity.User
import model.table.UserTable
import org.scalatest.BeforeAndAfterEach
import org.scalatestplus.play.PlaySpec
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class UserRepositorySpec extends PlaySpec with BeforeAndAfterEach {

    private val db = Database.forConfig("h2TestProp")
    private val userRepository = new UserRepository()
    private val userTable = TableQuery[UserTable]

    override def beforeEach(): Unit = {
        Await.result(db.run(userTable.schema.create), 2.seconds)
    }

    override def afterEach(): Unit = {
        Await.result(db.run(userTable.schema.drop), 2.seconds)
    }

    "UserRepository#findAll" should {
        "return all users" in {
            // setup
            val users = Seq(
                User(1, "testUserA", "testPassA"),
                User(2, "testUserB", "testPassB")
            )
            Await.result(db.run(userTable ++= users), 2.seconds)

            // do
            val result = Await.result(db.run(userRepository.findAll()), 2.seconds)

            // verify
            result must contain theSameElementsAs users
        }
    }
}
