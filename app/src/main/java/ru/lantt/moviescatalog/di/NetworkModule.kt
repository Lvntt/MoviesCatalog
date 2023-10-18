package ru.lantt.moviescatalog.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.lantt.moviescatalog.common.Constants.BASE_URL
import ru.lantt.moviescatalog.common.Constants.CONNECT_TIMEOUT_SEC
import ru.lantt.moviescatalog.common.Constants.READ_TIMEOUT_SEC
import ru.lantt.moviescatalog.common.Constants.WRITE_TIMEOUT_SEC
import ru.lantt.moviescatalog.data.network.api.AuthApiService
import java.util.concurrent.TimeUnit

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
) =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
//    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

private fun provideAuthApiService(
    retrofit: Retrofit
): AuthApiService =
    retrofit.create(AuthApiService::class.java)

fun provideNetworkModule(): Module = module {

    single {
        provideRetrofit(
            get(),
            BASE_URL
        )
    }

    single {
        provideLoggingInterceptor()
    }

    single {
        provideOkHttpClient(
            get()
        )
    }

    single {
        provideAuthApiService(get())
    }

}