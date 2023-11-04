package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.util.Constants.MIN_PASSWORD_LENGTH

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationErrorType? {
        return when {
            password.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            password.length < MIN_PASSWORD_LENGTH -> ValidationErrorType.INVALID_PASSWORD_LENGTH
            else -> null
        }
    }

}