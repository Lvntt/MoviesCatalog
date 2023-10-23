package ru.lantt.moviescatalog.presentation.viewmodel.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserProfileUseCase
import ru.lantt.moviescatalog.data.pagination.MoviesPagingSource
import ru.lantt.moviescatalog.presentation.uistate.main.MainUiState

class MainScreenViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    init {
        // TODO exception handling
        viewModelScope.launch {
            getUserProfileUseCase()
        }
    }

    // TODO 6 to constants
    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviesPagingSource(
            getMoviesUseCase = getMoviesUseCase,
            getUserIdFromLocalStorageUseCase = getUserIdFromLocalStorageUseCase,
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }.flow.cachedIn(viewModelScope)

    val mainUiState: State<MainUiState>
        get() = _mainUiState
    private val _mainUiState: MutableState<MainUiState> = mutableStateOf(
        MainUiState.Initial
    )

    private companion object {
        const val PAGE_SIZE = 6
    }

}