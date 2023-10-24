package ru.lantt.moviescatalog.presentation.ui.screen.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogDestinations
import ru.lantt.moviescatalog.presentation.ui.event.LaunchEvent
import ru.lantt.moviescatalog.presentation.viewmodel.launch.LaunchViewModel

@Composable
fun LaunchScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LaunchViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.launchFlow.collect { event ->
            when (event) {
                LaunchEvent.AuthenticationRequired -> navController.navigate(MoviesCatalogDestinations.AUTHORIZATION)
                LaunchEvent.RedirectionToMainRequired -> navController.navigate(MoviesCatalogDestinations.MAIN)
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.launch_screen_bg_pic),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.filmus_logo),
            contentDescription = null
        )
    }
}
