package ru.lantt.moviescatalog.data.validator

import android.webkit.URLUtil
import ru.lantt.moviescatalog.domain.validator.UrlValidator

class UrlValidatorImpl : UrlValidator {

    override fun isValid(url: String): Boolean {
        return URLUtil.isValidUrl(url)
    }
}