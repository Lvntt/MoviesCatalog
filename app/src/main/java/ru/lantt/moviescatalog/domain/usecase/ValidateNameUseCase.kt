package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ErrorType
import ru.lantt.moviescatalog.domain.entity.ValidationResult
import ru.lantt.moviescatalog.domain.util.Constants

class ValidateNameUseCase {

    operator fun invoke(name: String): ValidationResult {
        when {
            name.isEmpty() -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.EMPTY_FIELD
                )
            }
            name.length < Constants.MIN_NAME_LENGTH -> {
                return ValidationResult(
                    isSuccessful = false,
                    errorType = ErrorType.INVALID_NAME_LENGTH
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