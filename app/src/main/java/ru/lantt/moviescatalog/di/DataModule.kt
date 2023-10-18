package ru.lantt.moviescatalog.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.lantt.moviescatalog.data.datasource.TokenDataSource
import ru.lantt.moviescatalog.data.network.api.AuthApiService
import ru.lantt.moviescatalog.data.repository.AuthRepositoryImpl
import ru.lantt.moviescatalog.domain.repository.AuthRepository

private fun provideTokenDataSource(context: Context): TokenDataSource =
    TokenDataSource(context)

private fun provideAuthRepository(
    authApiService: AuthApiService,
    tokenDataSource: TokenDataSource
): AuthRepository =
    AuthRepositoryImpl(authApiService, tokenDataSource)

fun provideDataModule(): Module = module {

    single {
        provideTokenDataSource(androidContext().applicationContext)
    }

    single {
        provideAuthRepository(get(), get())
    }

}