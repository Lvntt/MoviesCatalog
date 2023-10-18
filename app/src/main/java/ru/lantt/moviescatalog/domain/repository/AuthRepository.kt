package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.LoginCredentials

interface AuthRepository {

    suspend fun login(loginCredentials: LoginCredentials)

    suspend fun logout()

}