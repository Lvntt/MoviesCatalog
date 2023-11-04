package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.util.Constants

class ValidateNameUseCase {

    operator fun invoke(name: String): ValidationErrorType? {
        return when {
            name.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            name.length < Constants.MIN_NAME_LENGTH -> ValidationErrorType.INVALID_NAME_LENGTH
            else -> null
        }
    }

}