package ru.lantt.moviescatalog.domain.repository

import ru.lantt.moviescatalog.domain.entity.Profile

interface UserRepository {

    suspend fun getAndSaveUserProfile(): Profile

}