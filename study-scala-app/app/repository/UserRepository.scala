package repository

import model.entity.User
import model.table.UserTable
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

import javax.inject.{Inject, Singleton}

@Singleton
class UserRepository @Inject() {
    private val userTable = TableQuery[UserTable]

    def findAll(): DBIO[Seq[User]] = userTable.result
}
