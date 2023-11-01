package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel

interface ReviewRepository {

    suspend fun addReview(movieId: String, reviewModifyModel: ReviewModifyModel)

    suspend fun editReview(movieId: String, id: String, reviewModifyModel: ReviewModifyModel)

    suspend fun deleteReview(movieId: String, id: String,)

}