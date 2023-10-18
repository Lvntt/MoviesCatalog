package ru.lantt.moviescatalog.domain.usecase

import android.util.Patterns
import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationResult {

        when {
            email.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.INVALID_EMAIL
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