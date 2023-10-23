package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.MovieDetails
import ru.lantt.moviescatalog.domain.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(id: String): MovieDetails
        = movieRepository.getMovieDetails(id)

}