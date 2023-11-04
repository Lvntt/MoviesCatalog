package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import ru.lantt.moviescatalog.common.Constants.GET_EDIT_USER_PROFILE_URL
import ru.lantt.moviescatalog.domain.entity.Profile

interface UserApiService {

    @GET(GET_EDIT_USER_PROFILE_URL)
    suspend fun getUserProfile(): Profile

    @PUT(GET_EDIT_USER_PROFILE_URL)
    suspend fun editUserProfile(@Body profile: Profile)

}