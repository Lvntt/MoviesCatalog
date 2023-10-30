package ru.lantt.moviescatalog.presentation.ui.screen.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.data.datasource.MockDetailedMovieSource
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieAbout
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieDescription
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieGenres
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieImage
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieInfo
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review.MovieMyReviewItem
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review.MovieReviewItem
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.MovieTopBar
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.ReviewDialog
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny

@Composable
fun MovieScreen(
    modifier: Modifier = Modifier
) {
    val movie = MockDetailedMovieSource.movie
    var isDialogOpened by remember { mutableStateOf(false) }

    if (isDialogOpened) {
        ReviewDialog(
            onDismissRequest = {
                isDialogOpened = false
            },
            onRatingChanged = {
                // TODO change VM state
            }
        )
    }

    Scaffold(
        topBar = {
            MovieTopBar(onBackButtonClick = {})
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .background(Gray900)
                .padding(paddingValues)
                .then(
                    if (isDialogOpened) {
                        Modifier.blur(15.dp)
                    } else {
                        Modifier
                    }
                )
        ) {
            item {
                MovieImage(posterLink = movie.poster)

                Spacer(modifier = Modifier.height(Padding20))
            }

            item {
                MovieInfo(
                    name = movie.name,
                    rating = movie.rating,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }

            item {
                MovieDescription(
                    description = movie.description,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }

            item {
                MovieGenres(
                    genres = movie.genres,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }

            item {
                MovieAbout(
                    movie = movie,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }

            item {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = PaddingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.reviews),
                        style = Label_B_16,
                        color = Color.White
                    )

                    if (movie.myReview == null) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Accent,
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.plus_icon),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.padding(PaddingTiny)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(MovieRegularPadding))
            }

            item {
                if (movie.myReview != null) {
                    MovieMyReviewItem(
                        review = movie.myReview,
                        modifier = modifier.padding(horizontal = PaddingMedium),
                        onEditClick = {
                            // TODO
                            isDialogOpened = !isDialogOpened
                        },
                        onDeleteClick = { /* TODO */ }
                    )

                    Spacer(modifier = Modifier.height(Padding20))
                }
            }

            items(count = movie.usersReviews.size) { reviewIndex ->
                val review = movie.usersReviews[reviewIndex]

                MovieReviewItem(
                    review = review,
                    modifier = modifier.padding(horizontal = PaddingMedium)
                )

                Spacer(modifier = Modifier.height(Padding20))
            }
        }
    }

}


@Preview
@Composable
fun MovieScreenPreview() {
    MovieScreen()
}