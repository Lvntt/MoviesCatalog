package ru.lantt.moviescatalog.domain.entity

data class Profile(
    val id: String,
    val nickName: String?,
    val email: String,
    val avatarLink: String?,
    val name: String,
    val birthDate: String,
    val gender: Int
)
