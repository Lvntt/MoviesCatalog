package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.util.Constants.MIN_USERNAME_LENGTH

class ValidateLoginUseCase {

    operator fun invoke(username: String): ValidationErrorType? {
        return when {
            username.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            username.length < MIN_USERNAME_LENGTH -> ValidationErrorType.INVALID_LOGIN_LENGTH
            else -> null
        }
    }

}