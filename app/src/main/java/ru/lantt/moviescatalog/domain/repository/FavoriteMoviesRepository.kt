package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.MoviesListModel

interface FavoriteMoviesRepository {

    suspend fun getFavoriteMovies(): MoviesListModel

    suspend fun addFavoriteMovie(id: String)

    suspend fun deleteFavoriteMovie(id: String)

}