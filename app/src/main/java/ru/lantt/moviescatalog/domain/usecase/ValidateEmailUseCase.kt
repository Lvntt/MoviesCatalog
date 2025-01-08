package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.validator.EmailValidator

class ValidateEmailUseCase(
    private val emailValidator: EmailValidator,
) {

    operator fun invoke(email: String): ValidationErrorType? {
        return when {
            email.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            !emailValidator.isValid(email) -> ValidationErrorType.INVALID_EMAIL
            else -> null
        }
    }

}