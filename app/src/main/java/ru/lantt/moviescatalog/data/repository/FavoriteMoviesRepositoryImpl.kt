package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.network.api.FavoriteMoviesApiService
import ru.lantt.moviescatalog.data.network.mapper.MovieNetworkMapper
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.repository.FavoriteMoviesRepository

class FavoriteMoviesRepositoryImpl(
    private val favoriteMoviesApiService: FavoriteMoviesApiService,
    private val movieNetworkMapper: MovieNetworkMapper
) : FavoriteMoviesRepository {

    override suspend fun getFavoriteMovies(): List<Movie> {
        val response = favoriteMoviesApiService.getFavoriteMovies()
        return movieNetworkMapper.fromEntityList(response.movies ?: emptyList())
    }

}