package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ReviewModifyModel
import ru.lantt.moviescatalog.domain.repository.ReviewRepository

class AddReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movieId: String,
        reviewModifyModel: ReviewModifyModel
    ) = reviewRepository.addReview(movieId, reviewModifyModel)

}