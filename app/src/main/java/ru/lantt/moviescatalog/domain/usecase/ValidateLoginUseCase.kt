package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult
import ru.lantt.moviescatalog.domain.util.Constants.MIN_USERNAME_LENGTH

class ValidateLoginUseCase {

    operator fun invoke(username: String): ValidationResult {
        when {
            username.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            username.length < MIN_USERNAME_LENGTH -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.INVALID_LOGIN_LENGTH
                )
            }
            else -> {
                return ValidationResult(
                    isSuccessful = true
                )
            }
        }
    }

}