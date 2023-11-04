package ru.lantt.moviescatalog.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.lantt.moviescatalog.common.Constants.GET_MOVIES_URL
import ru.lantt.moviescatalog.common.Constants.GET_MOVIE_DETAILS_URL
import ru.lantt.moviescatalog.common.Constants.ID_PATH
import ru.lantt.moviescatalog.common.Constants.PAGE_PATH
import ru.lantt.moviescatalog.domain.entity.MovieDetailsModel
import ru.lantt.moviescatalog.data.model.MoviesPagedListModel

interface MovieApiService {

    @GET(GET_MOVIES_URL)
    suspend fun getMovies(@Path(PAGE_PATH) page: Int): MoviesPagedListModel

    @GET(GET_MOVIE_DETAILS_URL)
    suspend fun getMovieDetails(@Path(ID_PATH) id: String): MovieDetailsModel

}