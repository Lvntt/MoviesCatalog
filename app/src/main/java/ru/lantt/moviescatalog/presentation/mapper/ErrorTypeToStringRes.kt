package ru.lantt.moviescatalog.presentation.mapper

import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType

object ErrorTypeToStringRes {

    val errors = mapOf(
        ValidationErrorType.EMPTY_FIELD to R.string.empty_field_error,
        ValidationErrorType.INVALID_LOGIN_LENGTH to R.string.invalid_login_length_error,
        ValidationErrorType.INVALID_PASSWORD_LENGTH to R.string.invalid_password_length_error,
        ValidationErrorType.INVALID_NAME_LENGTH to R.string.invalid_name_length_error,
        ValidationErrorType.PASSWORDS_MISMATCH to R.string.password_mismatch_error,
        ValidationErrorType.INVALID_EMAIL to R.string.invalid_email_error,
        ValidationErrorType.INVALID_DATE_OF_BIRTH to R.string.invalid_date_of_birth_error,
        ValidationErrorType.INVALID_URL to R.string.invalid_url_error,
    )

}