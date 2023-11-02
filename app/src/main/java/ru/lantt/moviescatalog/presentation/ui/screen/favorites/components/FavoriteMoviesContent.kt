package ru.lantt.moviescatalog.presentation.ui.screen.favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.Movie
import ru.lantt.moviescatalog.presentation.navigation.MoviesCatalogDestinations
import ru.lantt.moviescatalog.presentation.ui.theme.DefaultPaddingBetweenElements
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_15
import ru.lantt.moviescatalog.presentation.ui.theme.Title_2_B_20

@Composable
fun FavoriteMoviesContent(
    favorites: List<Movie>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .background(Gray900)
            .then(modifier)
    ) {
        if (favorites.isEmpty()) {
            item {
                Spacer(modifier = Modifier.height(100.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.you_have_no_favorites_yet),
                        style = Title_2_B_20,
                        color = Color.White,
                    )
                    Text(
                        text = stringResource(id = R.string.choose_and_add_favorites),
                        style = Text_R_15,
                        color = Color.White,
                    )
                }
            }
        }

        val groupSize = 3
        val groupsCount = favorites.size / groupSize

        items(groupsCount) { groupIndex ->
            val firstMovie = favorites.getOrNull(groupIndex * 3)
            val secondMovie = favorites.getOrNull(groupIndex * 3 + 1)
            val thirdMovie = favorites.getOrNull(groupIndex * 3 + 2)

            if (firstMovie == null) return@items
            Column(
                modifier = Modifier.padding(
                    start = PaddingMedium,
                    end = PaddingMedium,
                    top = Padding20
                )
            ) {
                Row {
                    RegularMovieCard(
                        movie = firstMovie,
                        onMovieClick = {
                            navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                        },
                        modifier = Modifier.weight(1f)
                    )

                    if (secondMovie != null) {
                        Spacer(modifier = Modifier.width(DefaultPaddingBetweenElements))

                        RegularMovieCard(
                            movie = secondMovie,
                            onMovieClick = {
                                navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Padding20))

                if (thirdMovie != null) {
                    WideMovieCard(
                        movie = thirdMovie,
                        onMovieClick = {
                            navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                        },
                    )
                }
            }
        }

        item {
            val firstMovie = favorites.getOrNull(groupsCount * groupSize)
            val secondMovie = favorites.getOrNull(groupsCount * groupSize + 1)

            Row(
                modifier = Modifier.padding(
                    start = PaddingMedium,
                    end = PaddingMedium,
                    top = Padding20
                )
            ) {
                if (firstMovie != null && secondMovie != null) {
                    RegularMovieCard(
                        movie = firstMovie,
                        onMovieClick = {
                            navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                        },
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(DefaultPaddingBetweenElements))

                    RegularMovieCard(
                        movie = secondMovie,
                        onMovieClick = {
                            navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                if (firstMovie != null) {
                    WideMovieCard(
                        movie = firstMovie,
                        onMovieClick = {
                            navController.navigate("${MoviesCatalogDestinations.MOVIE}/$it")
                        },
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(Padding20))
        }
    }
}