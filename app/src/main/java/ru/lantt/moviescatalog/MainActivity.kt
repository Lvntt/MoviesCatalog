package ru.lantt.moviescatalog

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import ru.lantt.moviescatalog.presentation.navigation.RootNavigation
import ru.lantt.moviescatalog.presentation.ui.theme.Main
import ru.lantt.moviescatalog.presentation.ui.theme.MoviesCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val activity = (LocalContext.current as? Activity)

            MoviesCatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Main
                ) {
                    RootNavigation(
                        navController = navController,
                        onCloseApp = {
                            activity?.finish()
                        }
                    )
                }
            }
        }
    }
}