package ru.lantt.moviescatalog.presentation.mapper

import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.ErrorType

object ErrorTypeToStringRes {

    val errors = mapOf(
        ErrorType.EMPTY_FIELD to R.string.empty_field_error,
        ErrorType.INVALID_LOGIN_LENGTH to R.string.invalid_login_length_error,
        ErrorType.INVALID_PASSWORD_LENGTH to R.string.invalid_password_length_error,
        ErrorType.INVALID_NAME_LENGTH to R.string.invalid_name_length_error,
        ErrorType.PASSWORDS_MISMATCH to R.string.password_mismatch_error,
        ErrorType.INVALID_EMAIL to R.string.invalid_email_error,
        ErrorType.INVALID_DATE_OF_BIRTH to R.string.invalid_date_of_birth_error,
    )

}