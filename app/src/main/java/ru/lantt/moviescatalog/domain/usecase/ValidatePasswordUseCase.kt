package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult
import ru.lantt.moviescatalog.domain.util.Constants.MIN_PASSWORD_LENGTH

class ValidatePasswordUseCase {

    operator fun invoke(password: String): ValidationResult {
        when {
            password.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            password.length < MIN_PASSWORD_LENGTH -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.INVALID_PASSWORD_LENGTH
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