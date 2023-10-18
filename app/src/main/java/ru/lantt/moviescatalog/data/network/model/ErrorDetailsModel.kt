package ru.lantt.moviescatalog.data.network.model

data class ErrorDetailsModel(
    val rawValue: String?,
    val attemptedValue: String?,
    val errors: List<ErrorModel>,
    val validationState: Int,
    val isContainerNode: Boolean,
    val children: String?
)
