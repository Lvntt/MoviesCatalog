package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.datasource.UserDataSource
import ru.lantt.moviescatalog.data.network.api.UserApiService
import ru.lantt.moviescatalog.domain.entity.Profile
import ru.lantt.moviescatalog.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getAndSaveUserProfile(): Profile {
        val userProfile = userApiService.getUserProfile()
        userDataSource.saveUserId(userProfile.id)
        return userProfile
    }

    override suspend fun getUserProfile(): Profile =
        userApiService.getUserProfile()


    override suspend fun editUserProfile(profile: Profile) =
        userApiService.editUserProfile(profile)


    override fun getUserIdFromLocalStorage(): String? =
        userDataSource.fetchUserId()


    override fun checkUserExistence(): Boolean =
        userDataSource.fetchUserId() != null

}