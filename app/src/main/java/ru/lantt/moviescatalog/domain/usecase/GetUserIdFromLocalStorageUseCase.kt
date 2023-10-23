package ru.lantt.moviescatalog.domain.usecase

import ru.lantt.moviescatalog.data.datasource.UserDataSource

class GetUserIdFromLocalStorageUseCase(
    private val userDataSource: UserDataSource
) {

    operator fun invoke() = userDataSource.fetchUserId()

}