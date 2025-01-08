package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.validator.UrlValidator

class ValidateUrlUseCase(
    private val urlValidator: UrlValidator,
) {

    operator fun invoke(url: String): ValidationErrorType? {
        return when {
            url.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            !urlValidator.isValid(url) -> ValidationErrorType.INVALID_URL
            else -> null
        }
    }

}