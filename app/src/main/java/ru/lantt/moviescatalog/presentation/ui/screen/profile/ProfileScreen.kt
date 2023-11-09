package ru.lantt.moviescatalog.presentation.ui.screen.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.event.ProfileEvent
import ru.lantt.moviescatalog.presentation.ui.screen.common.ErrorScreen
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.ProfileScreenContent
import ru.lantt.moviescatalog.presentation.ui.screen.profile.components.shimmer.ShimmerProfileScreenContent
import ru.lantt.moviescatalog.presentation.uistate.profile.ProfileUiState
import ru.lantt.moviescatalog.presentation.viewmodel.profile.ProfileViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    goToAuthorizationScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val profileUiState by remember { viewModel.profileUiState }
    val profileContent by remember { viewModel.profileContent }

    val transition = rememberInfiniteTransition(label = "shimmerTransition")
    val shimmerStartOffsetX by transition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1250)
        ),
        label = "shimmer"
    )

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.profileEventFlow.collect { event ->
            when (event) {
                ProfileEvent.AuthenticationRequired -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.authentication_required),
                        Toast.LENGTH_SHORT
                    ).show()
                    goToAuthorizationScreen()
                }
                ProfileEvent.LogOut -> {
                    goToAuthorizationScreen()
                }
            }
        }
    }


    when (profileUiState) {
        ProfileUiState.Initial -> Unit
        ProfileUiState.Loading -> ShimmerProfileScreenContent(shimmerStartOffsetXProvider = { shimmerStartOffsetX })
        ProfileUiState.Error -> ErrorScreen(onRetry = viewModel::retry)
        is ProfileUiState.Success -> ProfileScreenContent(
            viewModel = viewModel,
            profile = profileContent,
            shimmerStartOffsetXProvider = { shimmerStartOffsetX },
            modifier = modifier
        )
    }
}