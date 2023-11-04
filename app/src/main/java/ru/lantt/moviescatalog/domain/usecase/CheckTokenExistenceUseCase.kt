package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.AuthRepository

class CheckTokenExistenceUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke() = authRepository.hasToken()

}