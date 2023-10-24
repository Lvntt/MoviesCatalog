package ru.lantt.moviescatalog.presentation.viewmodel.main

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ru.lantt.moviescatalog.data.pagination.MoviesPagingSource
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.usecase.CheckUserExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase

class MainScreenViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val checkUserExistenceUseCase: CheckUserExistenceUseCase,
    private val getAndSaveUserProfileUseCase: GetAndSaveUserProfileUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviesPagingSource(
            getMoviesUseCase = getMoviesUseCase,
            checkUserExistenceUseCase = checkUserExistenceUseCase,
            getUserIdFromLocalStorageUseCase = getUserIdFromLocalStorageUseCase,
            getAndSaveUserProfileUseCase = getAndSaveUserProfileUseCase,
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }.flow.cachedIn(viewModelScope)

}