package ru.lantt.moviescatalog.domain.usecase

import android.util.Patterns
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

class ValidateEmailUseCase {

    operator fun invoke(email: String): ValidationErrorType? {
        return when {
            email.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationErrorType.INVALID_EMAIL
            else -> null
        }
    }

}