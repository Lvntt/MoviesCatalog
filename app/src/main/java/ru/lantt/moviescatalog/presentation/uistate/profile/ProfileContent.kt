package ru.lantt.moviescatalog.presentation.uistate.profile

data class ProfileContent(
    val id: String = "",
    val nickname: String = "",
    val email: String = "",
    val emailErrorId: Int? = null,
    val avatarLink: String = "",
    val avatarLinkErrorId: Int? = null,
    val name: String = "",
    val nameErrorId: Int? = null,
    val birthDate: String = "",
    val birthDateErrorId: Int? = null,
    val gender: Int = 0
)
