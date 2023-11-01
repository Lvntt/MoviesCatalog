package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.lantt.moviescatalog.common.Constants.ADD_REVIEW_URL
import ru.lantt.moviescatalog.common.Constants.DELETE_REVIEW_URL
import ru.lantt.moviescatalog.common.Constants.EDIT_REVIEW_URL
import ru.lantt.moviescatalog.common.Constants.ID_PATH
import ru.lantt.moviescatalog.common.Constants.MOVIE_ID_PATH
import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel

interface ReviewApiService {

    @POST(ADD_REVIEW_URL)
    suspend fun addReview(
        @Path(MOVIE_ID_PATH) movieId: String,
        @Body reviewModifyModel: ReviewModifyModel
    )

    @PUT(EDIT_REVIEW_URL)
    suspend fun editReview(
        @Path(MOVIE_ID_PATH) movieId: String,
        @Path(ID_PATH) id: String,
        @Body reviewModifyModel: ReviewModifyModel
    )

    @DELETE(DELETE_REVIEW_URL)
    suspend fun deleteReview(
        @Path(MOVIE_ID_PATH) movieId: String,
        @Path(ID_PATH) id: String,
    )

}