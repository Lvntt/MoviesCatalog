package ru.lantt.moviescatalog.domain.usecase

import android.webkit.URLUtil
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

class ValidateUrlUseCase {

    operator fun invoke(url: String): ValidationErrorType? {
        return when {
            url.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            !URLUtil.isValidUrl(url) -> ValidationErrorType.INVALID_URL
            else -> null
        }
    }

}