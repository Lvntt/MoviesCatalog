package ru.lantt.moviescatalog.presentation.viewmodel.home

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.lantt.moviescatalog.data.pagination.MoviesPagingSource
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.usecase.CheckUserExistenceUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieUpdateModel

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val checkUserExistenceUseCase: CheckUserExistenceUseCase,
    private val getAndSaveUserProfileUseCase: GetAndSaveUserProfileUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    val updatedMovies: SnapshotStateMap<String, MovieUpdateModel>
        get() = _updatedMovies
    private val _updatedMovies: SnapshotStateMap<String, MovieUpdateModel> = mutableStateMapOf()

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviesPagingSource(
            getMoviesUseCase = getMoviesUseCase,
            checkUserExistenceUseCase = checkUserExistenceUseCase,
            getUserIdFromLocalStorageUseCase = getUserIdFromLocalStorageUseCase,
            getAndSaveUserProfileUseCase = getAndSaveUserProfileUseCase,
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }.flow.cachedIn(viewModelScope)

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUserUseCase()
        }
    }

}