package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.entity.MovieDetails

interface MovieRepository {

    suspend fun getMovies(page: Int): List<Movie>

    suspend fun getMovieDetails(id: String): MovieDetails

}