package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel
import ru.lantt.moviescatalog.domain.repository.ReviewRepository

class EditReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movieId: String,
        reviewId: String,
        reviewModifyModel: ReviewModifyModel
    ) = reviewRepository.editReview(movieId, reviewId, reviewModifyModel)

}