package ru.lantt.moviescatalog.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.lantt.moviescatalog.common.Constants.AUTHORIZATION_HEADER
import ru.lantt.moviescatalog.common.Constants.BEARER
import ru.lantt.moviescatalog.data.datasource.TokenDataSource

class AuthInterceptor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        if (request.header(AUTHORIZATION_HEADER) == null) {
            tokenDataSource.fetchToken()?.let {
                builder.addHeader(
                    name = AUTHORIZATION_HEADER,
                    value = "$BEARER $it"
                )
            }
        }

        return chain.proceed(builder.build())
    }

}