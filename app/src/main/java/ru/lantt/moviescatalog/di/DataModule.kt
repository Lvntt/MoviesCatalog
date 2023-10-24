package ru.lantt.moviescatalog.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.data.datasource.TokenDataSource
import ru.lantt.moviescatalog.data.datasource.UserDataSource
import ru.lantt.moviescatalog.data.network.api.AuthApiService
import ru.lantt.moviescatalog.data.network.api.MovieApiService
import ru.lantt.moviescatalog.data.network.api.UserApiService
import ru.lantt.moviescatalog.data.network.mapper.MovieNetworkMapper
import ru.lantt.moviescatalog.data.repository.AuthRepositoryImpl
import ru.lantt.moviescatalog.data.repository.MovieRepositoryImpl
import ru.lantt.moviescatalog.data.repository.UserRepositoryImpl
import ru.lantt.moviescatalog.domain.repository.AuthRepository
import ru.lantt.moviescatalog.domain.repository.MovieRepository
import ru.lantt.moviescatalog.domain.repository.UserRepository

private fun provideTokenDataSource(context: Context): TokenDataSource =
    TokenDataSource(context)

private fun provideUserDataSource(context: Context): UserDataSource =
    UserDataSource(context)

private fun provideAuthRepository(
    authApiService: AuthApiService,
    tokenDataSource: TokenDataSource
): AuthRepository =
    AuthRepositoryImpl(authApiService, tokenDataSource)

private fun provideMovieRepository(
    movieApiService: MovieApiService,
    movieNetworkMapper: MovieNetworkMapper
): MovieRepository =
    MovieRepositoryImpl(movieApiService, movieNetworkMapper)

private fun provideUserRepository(
    userApiService: UserApiService,
    userDataSource: UserDataSource
): UserRepository =
    UserRepositoryImpl(userApiService, userDataSource)

fun provideDataModule(): Module = module {

    single { provideTokenDataSource(androidContext().applicationContext) }

    single { provideUserDataSource(androidContext().applicationContext) }

    single { provideAuthRepository(get(), get()) }

    single { provideMovieRepository(get(), get()) }

    single { provideUserRepository(get(), get()) }

}