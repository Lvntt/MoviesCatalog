package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.entity.UserRegisterModel

interface AuthRepository {

    suspend fun login(loginCredentials: LoginCredentials)

    suspend fun register(userRegisterModel: UserRegisterModel)

    suspend fun logout()

    fun hasToken(): Boolean

}