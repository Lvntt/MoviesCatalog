package ru.lantt.moviescatalog.common

object Constants {
    const val EMPTY_STRING = ""
    const val TOKEN_PREFERENCES_KEY = "token_preferences_key"
    const val USER_TOKEN_KEY = "user_token_key"
    const val CONNECT_TIMEOUT_SEC = 60L
    const val WRITE_TIMEOUT_SEC = 60L
    const val READ_TIMEOUT_SEC = 60L
    const val BASE_URL = "https://react-midterm.kreosoft.space/api/"
    const val LOGIN_URL = "account/login"
    const val REGISTER_URL = "account/register"
    const val LOGOUT_URL = "account/logout"
    const val GET_EDIT_USER_PROFILE_URL = "account/profile"
    const val GET_MOVIES_URL = "movies/{page}"
    const val GET_MOVIE_DETAILS_URL = "movies/details/{id}"
    const val GET_FAVORITE_MOVIES_URL = "favorites"
    const val ADD_FAVORITE_MOVIE_URL = "favorites/{id}/add"
    const val DELETE_FAVORITE_MOVIE_URL = "favorites/{id}/delete"
    const val ADD_REVIEW_URL = "movie/{movieId}/review/add"
    const val EDIT_REVIEW_URL = "movie/{movieId}/review/{id}/edit"
    const val DELETE_REVIEW_URL = "movie/{movieId}/review/{id}/delete"
    const val PAGE_PATH = "page"
    const val ID_PATH = "id"
    const val MOVIE_ID_PATH = "movieId"
    const val USER_DATASTORE = "user_datastore"
    const val ID = "id"
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER = "Bearer"

}