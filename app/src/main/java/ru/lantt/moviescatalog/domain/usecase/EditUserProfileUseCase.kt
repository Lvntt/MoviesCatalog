package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.data.network.api.UserApiService
import ru.lantt.moviescatalog.domain.entity.Profile

class EditUserProfileUseCase(
    private val userApiService: UserApiService
) {

    suspend operator fun invoke(profile: Profile)
        = userApiService.editUserProfile(profile)

}