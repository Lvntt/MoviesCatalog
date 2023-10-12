package ru.lantt.moviescatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogNavigation
import ru.lantt.moviescatalog.presentation.ui.theme.MoviesCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MoviesCatalogTheme {
                MoviesCatalogNavigation(navController = navController)
            }
        }
    }
}