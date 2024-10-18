package service

import play.api.Logger
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import repository.UserRepository
import slick.jdbc.MySQLProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserService @Inject()(userRepository: UserRepository,
                            protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[MySQLProfile] {

    private val logger = Logger(this.getClass)

    def findAll(): Future[String] = {
        val action = userRepository.findAll()
        db.run(action).map(users => {
            users.map(user => {
                s"ID: ${user.id}, Name: ${user.name}"
            }).mkString("\n")
        }).recover({
            case e: Exception =>
                logger.error(e.getMessage)
                throw e
        })
    }
}