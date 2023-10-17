package ru.lantt.moviescatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogNavigation
import ru.lantt.moviescatalog.presentation.ui.theme.Main
import ru.lantt.moviescatalog.presentation.ui.theme.MoviesCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MoviesCatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Main
                ) {
                    MoviesCatalogNavigation(navController = navController)
                }
            }
        }
    }
}