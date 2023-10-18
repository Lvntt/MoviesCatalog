package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.UserRegisterModel
import ru.lantt.moviescatalog.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(userRegisterModel: UserRegisterModel) {
        authRepository.register(userRegisterModel)
    }

}