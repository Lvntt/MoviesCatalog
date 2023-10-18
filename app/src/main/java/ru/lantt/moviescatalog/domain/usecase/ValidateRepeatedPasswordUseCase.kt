package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult

class ValidateRepeatedPasswordUseCase {

    operator fun invoke(password: String, repeatedPassword: String): ValidationResult {
        when {
            password.isEmpty() || repeatedPassword.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            password != repeatedPassword -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.PASSWORDS_MISMATCH
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