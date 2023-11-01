package ru.lantt.moviescatalog.domain.entity

enum class ValidationErrorType {
    EMPTY_FIELD,
    INVALID_LOGIN_LENGTH,
    INVALID_PASSWORD_LENGTH,
    INVALID_NAME_LENGTH,
    PASSWORDS_MISMATCH,
    INVALID_EMAIL,
    INVALID_DATE_OF_BIRTH,
    INVALID_URL
}