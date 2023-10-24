package ru.lantt.moviescatalog.common

object Constants {
    const val EMPTY_STRING = ""
    const val TOKEN_PREFERENCES_KEY = "token_preferences_key"
    const val USER_TOKEN_KEY = "user_token_key"
    const val CONNECT_TIMEOUT_SEC = 20L
    const val WRITE_TIMEOUT_SEC = 20L
    const val READ_TIMEOUT_SEC = 20L
    const val BASE_URL = "https://react-midterm.kreosoft.space/api/"
    const val LOGIN_URL = "account/login"
    const val REGISTER_URL = "account/register"
    const val LOGOUT_URL = "account/logout"
    const val GET_USER_PROFILE_URL = "account/profile"
    const val GET_MOVIES_URL = "movies/{page}"
    const val GET_MOVIE_DETAILS_URL = "movies/details/{id}"
    const val PAGE_PATH = "page"
    const val ID_PATH = "id"
    const val USER_DATASTORE = "user_datastore"
    const val ID = "id"
    const val AUTHORIZATION_HEADER = "Authorization"
    const val BEARER = "Bearer"
}