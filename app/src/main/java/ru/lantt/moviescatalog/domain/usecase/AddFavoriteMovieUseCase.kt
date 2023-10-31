package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.FavoriteMoviesRepository

class AddFavoriteMovieUseCase(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) {

    suspend operator fun invoke(id: String) = favoriteMoviesRepository.addFavoriteMovie(id)

}