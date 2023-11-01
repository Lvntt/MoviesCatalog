package ru.lantt.moviescatalog.data.repository

import ru.lantt.moviescatalog.data.network.api.MovieApiService
import ru.lantt.moviescatalog.data.network.mapper.MovieNetworkMapper
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.entity.MovieDetailsModel
import ru.lantt.moviescatalog.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieNetworkMapper: MovieNetworkMapper
) : MovieRepository {

    override suspend fun getMovies(page: Int): List<Movie> {
        val response = movieApiService.getMovies(page)
        return movieNetworkMapper.fromEntityList(response.movies)
    }

    override suspend fun getMovieDetails(id: String): MovieDetailsModel
        = movieApiService.getMovieDetails(id)

}