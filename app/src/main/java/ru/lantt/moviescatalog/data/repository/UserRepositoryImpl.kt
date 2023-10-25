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

    override fun getUserIdFromLocalStorage(): String? {
        return userDataSource.fetchUserId()
    }

    override fun checkUserExistence(): Boolean {
        return userDataSource.fetchUserId() != null
    }

}