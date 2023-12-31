package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.MovieDetailsModel
import ru.lantt.moviescatalog.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(id: String): MovieDetailsModel
        = movieRepository.getMovieDetails(id)

}