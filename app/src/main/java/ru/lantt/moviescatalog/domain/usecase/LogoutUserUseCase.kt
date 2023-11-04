package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.AuthRepository

class LogoutUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() {
        authRepository.logout()
    }

}