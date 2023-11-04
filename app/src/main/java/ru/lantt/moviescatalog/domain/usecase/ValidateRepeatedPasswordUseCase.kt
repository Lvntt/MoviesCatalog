package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

class ValidateRepeatedPasswordUseCase {

    operator fun invoke(password: String, repeatedPassword: String): ValidationErrorType? {
        return when {
            password.isEmpty() || repeatedPassword.isEmpty() -> ValidationErrorType.EMPTY_FIELD
            password != repeatedPassword -> ValidationErrorType.PASSWORDS_MISMATCH
            else -> null
        }
    }

}