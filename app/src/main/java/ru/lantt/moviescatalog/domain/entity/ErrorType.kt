package ru.lantt.moviescatalog.domain.entity

enum class ErrorType {
    EMPTY_FIELD,
    INVALID_LOGIN_LENGTH,
    INVALID_PASSWORD_LENGTH,
    INVALID_NAME_LENGTH,
    PASSWORDS_MISMATCH,
    INVALID_EMAIL,
    INVALID_DATE_OF_BIRTH,
}