package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.UserRepository

class GetUserIdFromLocalStorageUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke() = userRepository.getUserIdFromLocalStorage()

}