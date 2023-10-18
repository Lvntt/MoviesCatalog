package ru.lantt.moviescatalog.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.lantt.moviescatalog.domain.entity.LoginCredentials
import ru.lantt.moviescatalog.domain.usecase.LoginUserUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes.BAD_REQUEST
import ru.lantt.moviescatalog.presentation.uistate.auth.login.LoginContent
import ru.lantt.moviescatalog.presentation.uistate.auth.login.LoginUiState

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent: MutableState<LoginContent> = mutableStateOf(LoginContent())

    val loginUiState: State<LoginUiState>
        get() = _loginUiState
    private val _loginUiState: MutableState<LoginUiState> = mutableStateOf(LoginUiState.Initial)

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                BAD_REQUEST -> {
                    _loginContent.value = _loginContent.value.copy(isError = true)
                    _loginUiState.value = LoginUiState.Initial
                }
                else -> _loginUiState.value = LoginUiState.Error
            }
            else -> _loginUiState.value = LoginUiState.Error
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
        return _loginContent.value.login.isNotEmpty() && _loginContent.value.password.isNotEmpty()
    }

    fun logIn() {
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase(
                LoginCredentials(
                    username = _loginContent.value.login,
                    password = _loginContent.value.password
                )
            )
            _loginUiState.value = LoginUiState.Success
        }
    }

}