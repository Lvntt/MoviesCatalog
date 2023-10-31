package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.lantt.moviescatalog.common.Constants.ADD_FAVORITE_MOVIE_URL
import ru.lantt.moviescatalog.common.Constants.DELETE_FAVORITE_MOVIE_URL
import ru.lantt.moviescatalog.common.Constants.GET_FAVORITE_MOVIES_URL
import ru.lantt.moviescatalog.common.Constants.ID_PATH
import ru.lantt.moviescatalog.domain.entity.MoviesListModel

interface FavoriteMoviesApiService {

    @GET(GET_FAVORITE_MOVIES_URL)
    suspend fun getFavoriteMovies(): MoviesListModel

    @POST(ADD_FAVORITE_MOVIE_URL)
    suspend fun addFavoriteMovie(@Path(ID_PATH) id: String)

    @DELETE(DELETE_FAVORITE_MOVIE_URL)
    suspend fun deleteFavoriteMovie(@Path(ID_PATH) id: String)

}