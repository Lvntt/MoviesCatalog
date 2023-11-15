package ru.lantt.moviescatalog.presentation.viewmodel.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.usecase.LoginUserUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes.BAD_REQUEST
import ru.lantt.moviescatalog.presentation.ui.event.LoginEvent
import ru.lantt.moviescatalog.presentation.uistate.auth.login.LoginContent
import ru.lantt.moviescatalog.presentation.uistate.auth.login.LoginState

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent: MutableState<LoginContent> = mutableStateOf(
        LoginContent()
    )

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEventFlow = loginEventChannel.receiveAsFlow()

    val loginState: State<LoginState>
        get() = _loginState
    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState.Editing)

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                BAD_REQUEST -> {
                    _loginContent.value = _loginContent.value.copy(isError = true)
                    _loginState.value = LoginState.Editing
                }
                else -> {
                    viewModelScope.launch {
                        loginEventChannel.send(LoginEvent.LoginError)
                    }
                }
            }
            else -> {
                viewModelScope.launch {
                    loginEventChannel.send(LoginEvent.LoginError)
                }
            }
        }
    }

    fun setLogin(login: String) {
        _loginContent.value = _loginContent.value.copy(login = login)
    }

    fun setPassword(password: String) {
        _loginContent.value = _loginContent.value.copy(password = password)
    }

    fun changePasswordVisibility() {
        val passwordIsVisible = _loginContent.value.passwordIsVisible
        _loginContent.value = _loginContent.value.copy(passwordIsVisible = !passwordIsVisible)
    }

    fun canLogIn(): Boolean {
        return _loginContent.value.login.isNotEmpty()
                && _loginContent.value.password.isNotEmpty()
                && _loginState.value !is LoginState.Loading
    }

    fun logIn() {
        _loginState.value = LoginState.Loading
        _loginContent.value = _loginContent.value.copy(isError = false)
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase(
                with (_loginContent.value) {
                    LoginCredentials(
                        username = login,
                        password = password
                    )
                }
            )
            loginEventChannel.send(LoginEvent.LoggedIn)
        }
    }

}