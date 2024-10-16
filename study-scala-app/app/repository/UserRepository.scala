package repository

import model.entity.User
import model.table.UserTable
import play.api.Logger
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
    private val dbConfig = dbConfigProvider.get[MySQLProfile]
    private val userTable = TableQuery[UserTable]
    private val logger = Logger(this.getClass)

    def findAll(): Future[Seq[User]] = {
        val action = userTable.result
        dbConfig.db.run(action).recover({
            case e: Exception =>
                logger.error(e.getMessage)
                Seq.empty
        })
    }
}
