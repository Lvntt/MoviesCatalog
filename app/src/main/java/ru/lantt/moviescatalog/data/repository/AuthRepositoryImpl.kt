package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.datasource.TokenDataSource
import ru.lantt.moviescatalog.data.network.api.AuthApiService
import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.entity.UserRegisterModel
import ru.lantt.moviescatalog.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {

    override suspend fun login(loginCredentials: LoginCredentials) {
        val response = authApiService.login(loginCredentials)
        tokenDataSource.saveToken(response.token)
    }

    override suspend fun register(userRegisterModel: UserRegisterModel) {
        val response = authApiService.register(userRegisterModel)
        tokenDataSource.saveToken(response.token)
    }

    override suspend fun logout() {
        authApiService.logout()
        tokenDataSource.deleteToken()
    }

}