package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.GET
import ru.lantt.moviescatalog.common.Constants.GET_FAVORITE_MOVIES_URL
import ru.lantt.moviescatalog.domain.entity.MoviesListModel

interface FavoriteMoviesApiService {

    @GET(GET_FAVORITE_MOVIES_URL)
    suspend fun getFavoriteMovies(): MoviesListModel

}