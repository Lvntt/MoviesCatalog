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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.domain.usecase.GetMovieDetailsUseCase
import ru.lantt.moviescatalog.domain.usecase.GetMoviesUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserIdFromLocalStorageUseCase
import ru.lantt.moviescatalog.domain.usecase.GetAndSaveUserProfileUseCase
import ru.lantt.moviescatalog.data.pagination.MoviesPagingSource
import ru.lantt.moviescatalog.presentation.common.ErrorCodes.BAD_REQUEST
import ru.lantt.moviescatalog.presentation.ui.event.MainEvent
import ru.lantt.moviescatalog.presentation.uistate.main.MainUiState

class MainScreenViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getAndSaveUserProfileUseCase: GetAndSaveUserProfileUseCase,
    private val getUserIdFromLocalStorageUseCase: GetUserIdFromLocalStorageUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    val mainUiState: State<MainUiState>
        get() = _mainUiState
    private val _mainUiState: MutableState<MainUiState> = mutableStateOf(
        MainUiState.Initial
    )

    private val mainEventChannel = Channel<MainEvent>()
    val mainEventFlow = mainEventChannel.receiveAsFlow()

    private val mainScreenExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                BAD_REQUEST -> {
                    mainEventChannel.trySend(MainEvent.AuthenticationRequired)
                    _mainUiState.value = MainUiState.Error
                }
                else -> _mainUiState.value = MainUiState.Error
            }
            else -> _mainUiState.value = MainUiState.Error
        }
    }

    init {
        _mainUiState.value = MainUiState.Loading
        viewModelScope.launch(Dispatchers.IO + mainScreenExceptionHandler) {
            getAndSaveUserProfileUseCase()
            _mainUiState.value = MainUiState.Success
        }
    }

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviesPagingSource(
            getMoviesUseCase = getMoviesUseCase,
            getUserIdFromLocalStorageUseCase = getUserIdFromLocalStorageUseCase,
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }.flow.cachedIn(viewModelScope)

    fun retry() {
        _mainUiState.value = MainUiState.Loading
        viewModelScope.launch(Dispatchers.IO + mainScreenExceptionHandler) {
            getAndSaveUserProfileUseCase()
            _mainUiState.value = MainUiState.Success
        }
    }

    private companion object {
        const val PAGE_SIZE = 6
    }

}