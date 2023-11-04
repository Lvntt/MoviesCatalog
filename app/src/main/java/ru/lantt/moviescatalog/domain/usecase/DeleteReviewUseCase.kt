package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.ReviewRepository

class DeleteReviewUseCase(
    private val reviewRepository: ReviewRepository
) {

    suspend operator fun invoke(
        movieId: String,
        reviewId: String
    ) = reviewRepository.deleteReview(movieId, reviewId)

}