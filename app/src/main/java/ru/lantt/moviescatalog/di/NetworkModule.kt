package ru.lantt.moviescatalog.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.lantt.moviescatalog.common.Constants.BASE_URL
import ru.lantt.moviescatalog.common.Constants.CONNECT_TIMEOUT_SEC
import ru.lantt.moviescatalog.common.Constants.READ_TIMEOUT_SEC
import ru.lantt.moviescatalog.common.Constants.WRITE_TIMEOUT_SEC
import ru.lantt.moviescatalog.data.datasource.TokenDataSource
import ru.lantt.moviescatalog.data.network.api.AuthApiService
import ru.lantt.moviescatalog.data.network.api.FavoriteMoviesApiService
import ru.lantt.moviescatalog.data.network.api.MovieApiService
import ru.lantt.moviescatalog.data.network.api.ReviewApiService
import ru.lantt.moviescatalog.data.network.api.UserApiService
import ru.lantt.moviescatalog.data.network.interceptor.AuthInterceptor
import ru.lantt.moviescatalog.data.network.mapper.MovieNetworkMapper
import ru.lantt.moviescatalog.di.Labels.REGULAR_HTTP_CLIENT_LABEL
import ru.lantt.moviescatalog.di.Labels.REGULAR_RETROFIT_LABEL
import ru.lantt.moviescatalog.di.Labels.TOKEN_HTTP_CLIENT_LABEL
import ru.lantt.moviescatalog.di.Labels.TOKEN_RETROFIT_LABEL
import java.util.concurrent.TimeUnit

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideAuthInterceptor(
    tokenDataSource: TokenDataSource
): AuthInterceptor =
    AuthInterceptor(tokenDataSource)

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

private fun provideTokenOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    authInterceptor: AuthInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit =
    Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideMovieNetworkMapper(): MovieNetworkMapper =
    MovieNetworkMapper()

private fun provideAuthApiService(
    retrofit: Retrofit
): AuthApiService =
    retrofit.create(AuthApiService::class.java)

private fun provideMovieApiService(
    retrofit: Retrofit
): MovieApiService =
    retrofit.create(MovieApiService::class.java)

private fun provideUserApiService(
    retrofit: Retrofit
): UserApiService =
    retrofit.create(UserApiService::class.java)

private fun provideFavoriteMoviesApiService(
    retrofit: Retrofit
): FavoriteMoviesApiService =
    retrofit.create(FavoriteMoviesApiService::class.java)

private fun provideReviewApiService(
    retrofit: Retrofit
): ReviewApiService =
    retrofit.create(ReviewApiService::class.java)

fun provideNetworkModule(): Module = module {

    single(named(REGULAR_RETROFIT_LABEL)) { provideRetrofit(get(named(REGULAR_HTTP_CLIENT_LABEL)), BASE_URL) }

    single(named(TOKEN_RETROFIT_LABEL)) { provideRetrofit(get(named(TOKEN_HTTP_CLIENT_LABEL)), BASE_URL) }

    single { provideLoggingInterceptor() }

    single { provideAuthInterceptor(get()) }

    single(named(REGULAR_HTTP_CLIENT_LABEL)) { provideOkHttpClient(get()) }

    single(named(TOKEN_HTTP_CLIENT_LABEL)) { provideTokenOkHttpClient(get(), get()) }

    single { provideAuthApiService(get(named(REGULAR_RETROFIT_LABEL))) }

    single { provideMovieApiService(get(named(REGULAR_RETROFIT_LABEL))) }

    single { provideUserApiService(get(named(TOKEN_RETROFIT_LABEL))) }

    single { provideMovieNetworkMapper() }

    single { provideFavoriteMoviesApiService(get(named(TOKEN_RETROFIT_LABEL))) }

    single { provideReviewApiService(get(named(TOKEN_RETROFIT_LABEL))) }

}