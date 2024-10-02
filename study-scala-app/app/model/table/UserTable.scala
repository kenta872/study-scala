package model.table

import model.entity.User
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class UserTable(tag: Tag)() extends Table[User](tag, "user_tbl") {
    private def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

    private def name: Rep[String] = column[String]("name")

    private def password: Rep[String] = column[String]("password")

    def * = (id, name, password) <> ((User.apply _).tupled, User.unapply)

}
