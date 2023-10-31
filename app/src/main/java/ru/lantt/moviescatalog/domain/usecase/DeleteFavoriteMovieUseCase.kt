package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.FavoriteMoviesRepository

class DeleteFavoriteMovieUseCase(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) {

    suspend operator fun invoke(id: String) = favoriteMoviesRepository.deleteFavoriteMovie(id)

}