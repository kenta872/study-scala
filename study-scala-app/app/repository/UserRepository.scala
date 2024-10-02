package repository

import model.entity.User
import model.table.UserTable
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
    private val dbConfig = dbConfigProvider.get[JdbcProfile]
    private val userTable = TableQuery[UserTable]

    def findAll(): Future[Seq[User]] = {
        val action = userTable.result
        dbConfig.db.run(action).recover({
            case e: Exception =>
                println(e.getMessage)
                Seq.empty
        })
    }
}
