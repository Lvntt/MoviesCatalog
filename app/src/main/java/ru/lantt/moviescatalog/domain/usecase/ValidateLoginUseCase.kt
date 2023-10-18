package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult
import ru.lantt.moviescatalog.domain.util.Constants.MIN_LOGIN_LENGTH

class ValidateLoginUseCase {

    operator fun invoke(login: String): ValidationResult {
        when {
            login.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            login.length < MIN_LOGIN_LENGTH -> {
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