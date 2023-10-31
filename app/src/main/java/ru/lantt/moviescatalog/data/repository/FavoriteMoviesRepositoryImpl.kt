package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.network.api.FavoriteMoviesApiService
import ru.lantt.moviescatalog.domain.entity.MoviesListModel
import ru.lantt.moviescatalog.domain.repository.FavoriteMoviesRepository

class FavoriteMoviesRepositoryImpl(
    private val favoriteMoviesApiService: FavoriteMoviesApiService
) : FavoriteMoviesRepository {

    override suspend fun getFavoriteMovies(): MoviesListModel
        = favoriteMoviesApiService.getFavoriteMovies()

    override suspend fun addFavoriteMovie(id: String)
        = favoriteMoviesApiService.addFavoriteMovie(id)

    override suspend fun deleteFavoriteMovie(id: String)
        = favoriteMoviesApiService.deleteFavoriteMovie(id)

}