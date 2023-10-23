package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.Profile
import ru.lantt.moviescatalog.domain.repository.UserRepository

class GetAndSaveUserProfileUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Profile
        = userRepository.getAndSaveUserProfile()

}