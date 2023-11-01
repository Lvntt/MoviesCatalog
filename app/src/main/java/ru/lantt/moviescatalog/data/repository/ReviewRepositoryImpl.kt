package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.network.api.ReviewApiService
import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel
import ru.lantt.moviescatalog.domain.repository.ReviewRepository

class ReviewRepositoryImpl(
    private val reviewApiService: ReviewApiService
) : ReviewRepository {

    override suspend fun addReview(movieId: String, reviewModifyModel: ReviewModifyModel)
        = reviewApiService.addReview(movieId, reviewModifyModel)

    override suspend fun editReview(movieId: String, id: String, reviewModifyModel: ReviewModifyModel)
        = reviewApiService.editReview(movieId, id, reviewModifyModel)

    override suspend fun deleteReview(movieId: String, id: String)
        = reviewApiService.deleteReview(movieId, id)

}