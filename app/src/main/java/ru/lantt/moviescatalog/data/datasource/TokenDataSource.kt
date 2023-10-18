package ru.lantt.moviescatalog.data.datasource

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import ru.lantt.moviescatalog.common.Constants.TOKEN_PREFERENCES_KEY
import ru.lantt.moviescatalog.common.Constants.USER_TOKEN_KEY

class TokenDataSource(context: Context) {

    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        TOKEN_PREFERENCES_KEY,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun fetchToken(): String? {
        return sharedPreferences.getString(USER_TOKEN_KEY, null)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(USER_TOKEN_KEY, token)
            .apply()
    }

}