package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.UserRepository

class CheckUserExistenceUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Boolean
        = userRepository.checkUserExistence()

}