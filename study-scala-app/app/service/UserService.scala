package service

import model.entity.User
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

    def findAll(): Future[Seq[User]] = {
        db.run(userRepository.findAll()).recover {
            case e: Exception =>
                logger.error(s"Failed to find all users: ${e.getMessage}", e)
                throw e
        }
    }
}
