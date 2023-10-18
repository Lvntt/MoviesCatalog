package ru.lantt.moviescatalog.data.network.model

data class RegistrationErrorModel(
    val message: String,
    val errors: Map<String, ErrorDetailsModel>
)
