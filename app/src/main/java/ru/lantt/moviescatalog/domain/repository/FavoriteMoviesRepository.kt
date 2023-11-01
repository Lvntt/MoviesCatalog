package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.Movie

interface FavoriteMoviesRepository {

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun addFavoriteMovie(id: String)

    suspend fun deleteFavoriteMovie(id: String)

}