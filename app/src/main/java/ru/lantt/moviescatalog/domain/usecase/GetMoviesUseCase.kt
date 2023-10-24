package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(page: Int): List<Movie>
        = movieRepository.getMovies(page)

}