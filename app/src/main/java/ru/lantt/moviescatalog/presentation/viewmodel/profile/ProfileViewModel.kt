package ru.lantt.moviescatalog.presentation.viewmodel.profile

import android.util.Log
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
import ru.lantt.moviescatalog.common.Constants
import ru.lantt.moviescatalog.domain.entity.Profile
import ru.lantt.moviescatalog.domain.entity.ValidationErrorType
import ru.lantt.moviescatalog.domain.usecase.EditUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.GetUserProfileUseCase
import ru.lantt.moviescatalog.domain.usecase.LogoutUserUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateDateOfBirthUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateEmailUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateNameUseCase
import ru.lantt.moviescatalog.domain.usecase.ValidateUrlUseCase
import ru.lantt.moviescatalog.presentation.common.ErrorCodes
import ru.lantt.moviescatalog.presentation.mapper.ErrorTypeToStringRes
import ru.lantt.moviescatalog.presentation.ui.event.ProfileEvent
import ru.lantt.moviescatalog.presentation.uistate.profile.ProfileContent
import ru.lantt.moviescatalog.presentation.uistate.profile.ProfileUiState
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateUrlUseCase: ValidateUrlUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateDateOfBirthUseCase: ValidateDateOfBirthUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    val profileUiState: State<ProfileUiState>
        get() = _profileUiState
    private val _profileUiState: MutableState<ProfileUiState> = mutableStateOf(
        ProfileUiState.Initial
    )

    private val profileEventChannel = Channel<ProfileEvent>()
    val profileEventFlow = profileEventChannel.receiveAsFlow()

    val avatarLink: State<String>
        get() = _avatarLink
    private val _avatarLink: MutableState<String> = mutableStateOf("")

    val profileContent: State<ProfileContent>
        get() = _profileContent
    private val _profileContent: MutableState<ProfileContent> = mutableStateOf(
        ProfileContent()
    )

    private val profileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    viewModelScope.launch {
                        logoutUserUseCase()
                        profileEventChannel.send(ProfileEvent.AuthenticationRequired)
                    }
                    _profileUiState.value = ProfileUiState.Initial
                }

                else -> _profileUiState.value = ProfileUiState.Error
            }

            else -> _profileUiState.value = ProfileUiState.Error
        }
    }

    private var initialProfile: ProfileContent? = null

    init {
        loadUserProfile()
    }

    fun retry() {
        loadUserProfile()
    }

    fun setEmail(email: String) {
        val validationResult = validateEmailUseCase(email)
        val errorDescriptionId = getErrorDescription(validationResult)
        _profileContent.value = _profileContent.value.copy(
            email = email,
            emailErrorId = errorDescriptionId
        )
    }

    fun setAvatarLink(avatarLink: String) {
        val validationResult = validateUrlUseCase(avatarLink)
        val errorDescriptionId = getErrorDescription(validationResult)
        _profileContent.value = _profileContent.value.copy(
            avatarLink = avatarLink,
            avatarLinkErrorId = errorDescriptionId
        )
    }

    fun setName(name: String) {
        val validationResult = validateNameUseCase(name)
        val errorDescriptionId = getErrorDescription(validationResult)
        _profileContent.value = _profileContent.value.copy(
            name = name,
            nameErrorId = errorDescriptionId
        )
    }

    fun setGender(gender: Int) {
        _profileContent.value = _profileContent.value.copy(gender = gender)
    }

    fun setBirthDate(birthDateMills: Long) {
        val validationResult = validateDateOfBirthUseCase(birthDateMills)
        val errorDescriptionId = getErrorDescription(validationResult)
        val birthDate = getFormattedDateForUI(birthDateMills)
        _profileContent.value = _profileContent.value.copy(
            birthDate = birthDate,
            birthDateErrorId = errorDescriptionId
        )
    }

    fun canSave(): Boolean {
        return isProfileDataChanged() && isProfileDataValid()
    }

    fun editProfile() {
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            editUserProfileUseCase(
                with(_profileContent.value) {
                    Profile(
                        id = id,
                        nickName = nickname,
                        email = email,
                        avatarLink = avatarLink,
                        name = name,
                        birthDate = getFormattedDateForRequest(birthDate),
                        gender = gender
                    )
                }
            )
            _avatarLink.value = _profileContent.value.avatarLink
            initialProfile = _profileContent.value
        }
    }

    fun cancelEditing() {
        val initialProfile = this.initialProfile
        if (initialProfile != null) {
            _profileContent.value = initialProfile
        }
    }

    fun logOut() {
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            logoutUserUseCase()
            profileEventChannel.send(ProfileEvent.LogOut)
        }
    }

    private fun isProfileDataChanged(): Boolean {
        val initialProfile = this.initialProfile
        return initialProfile != null &&
                with(_profileContent.value) {
                    (initialProfile.email != email ||
                            initialProfile.name != name ||
                            initialProfile.avatarLink != avatarLink ||
                            initialProfile.gender != gender ||
                            initialProfile.birthDate != birthDate)
                }
    }

    private fun isProfileDataValid(): Boolean {
        with(_profileContent.value) {
            return email.isNotEmpty()
                    && avatarLink.isNotEmpty()
                    && name.isNotEmpty()
                    && birthDate.isNotEmpty()
                    && emailErrorId == null
                    && avatarLinkErrorId == null
                    && nameErrorId == null
                    && birthDateErrorId == null
        }
    }

    private fun loadUserProfile() {
        _profileUiState.value = ProfileUiState.Loading
        viewModelScope.launch(Dispatchers.IO + profileExceptionHandler) {
            val profile = getUserProfileUseCase()
            with(profile) {
                _profileContent.value = ProfileContent(
                    id = id,
                    nickname = nickName ?: "",
                    email = email,
                    avatarLink = avatarLink ?: "",
                    name = name,
                    birthDate = formatDateStringForUI(birthDate),
                    gender = gender
                )
            }
            initialProfile = _profileContent.value
            _avatarLink.value = profile.avatarLink ?: ""
            _profileUiState.value = ProfileUiState.Success
        }
    }

    private fun getErrorDescription(validationErrorType: ValidationErrorType?): Int? {
        if (validationErrorType == null) {
            return null
        }
        return ErrorTypeToStringRes.errors[validationErrorType]
    }

    private fun getFormattedDateForUI(dateOfBirthMillis: Long?): String {
        if (dateOfBirthMillis == null) {
            return Constants.EMPTY_STRING
        }
        val simpleDateFormat = SimpleDateFormat(UI_DATE_TIME_FORMAT, Locale.getDefault())
        return simpleDateFormat.format(dateOfBirthMillis)
    }

    private fun getFormattedDateForRequest(date: String): String {
        val sdf = SimpleDateFormat(UI_DATE_TIME_FORMAT, Locale.getDefault())
        return try {
            val date = sdf.parse(date) ?: Constants.EMPTY_STRING
            SimpleDateFormat(REQUEST_DATE_PATTERN, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            Log.e(PROFILE_VM_TAG, e.stackTraceToString())
            Constants.EMPTY_STRING
        }
    }

    private fun formatDateStringForUI(datetime: String): String {
        val sdf = SimpleDateFormat(INITIAL_DATE_TIME_FORMAT, Locale.getDefault())
        return try {
            val date = sdf.parse(datetime) ?: Constants.EMPTY_STRING
            SimpleDateFormat(UI_DATE_TIME_FORMAT, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            Log.e(PROFILE_VM_TAG, e.stackTraceToString())
            Constants.EMPTY_STRING
        }
    }

    private companion object {
        const val REQUEST_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val INITIAL_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        const val UI_DATE_TIME_FORMAT = "dd.MM.yyyy"
        const val PROFILE_VM_TAG = "ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel"
    }

}