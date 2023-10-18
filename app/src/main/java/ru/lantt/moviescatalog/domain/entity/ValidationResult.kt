package ru.lantt.moviescatalog.domain.entity

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorType: ErrorType? = null
)
