package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(loginCredentials: LoginCredentials) {
        authRepository.login(loginCredentials)
    }

}