package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.repository.FavoriteMoviesRepository

class GetFavoriteMoviesUseCase(
    private val favoriteMoviesRepository: FavoriteMoviesRepository
) {

    suspend operator fun invoke() = favoriteMoviesRepository.getFavoriteMovies()

}
