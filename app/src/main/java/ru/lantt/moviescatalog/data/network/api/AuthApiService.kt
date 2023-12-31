package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.lantt.moviescatalog.common.Constants.LOGIN_URL
import ru.lantt.moviescatalog.common.Constants.LOGOUT_URL
import ru.lantt.moviescatalog.common.Constants.REGISTER_URL
import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.entity.Token
import ru.lantt.moviescatalog.domain.entity.UserRegisterModel

interface AuthApiService {

    @POST(LOGIN_URL)
    suspend fun login(@Body loginCredentials: LoginCredentials): Token

    @POST(REGISTER_URL)
    suspend fun register(@Body userRegisterModel: UserRegisterModel): Token

    @POST(LOGOUT_URL)
    suspend fun logout()

}