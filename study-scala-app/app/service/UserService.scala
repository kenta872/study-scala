package service

import repository.UserRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserService @Inject()(userRepository: UserRepository)(implicit ec: ExecutionContext) {
    def findAll(): Future[String] = {
        userRepository.findAll().map { users =>
            users.map { user =>
                s"${user.name}-san(${user.id}) "
            }.mkString("[", ",", "]")
        }
    }
}