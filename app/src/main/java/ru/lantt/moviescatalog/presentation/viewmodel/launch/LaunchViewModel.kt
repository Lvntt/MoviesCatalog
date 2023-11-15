package ru.lantt.moviescatalog.presentation.viewmodel.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.lantt.moviescatalog.domain.usecase.CheckTokenExistenceUseCase
import ru.lantt.moviescatalog.presentation.ui.event.LaunchEvent

class LaunchViewModel(
    checkTokenExistenceUseCase: CheckTokenExistenceUseCase
) : ViewModel() {

    private val launchEventChannel = Channel<LaunchEvent>()
    val launchFlow = launchEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            if (checkTokenExistenceUseCase()) {
                launchEventChannel.send(LaunchEvent.RedirectionToHomeRequired)
            } else {
                launchEventChannel.send(LaunchEvent.AuthenticationRequired)
            }
        }
    }

}