package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

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
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review.MovieMyReviewItem
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review.MovieReviewItem
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_B_16
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.Padding20
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingMedium
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingTiny
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable
import ru.lantt.moviescatalog.presentation.uistate.movie.MovieDetailsContent
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@Composable
fun MovieScreenContent(
    movie: MovieDetailsContent,
    viewModel: MovieViewModel,
    shimmerStartOffsetX: Float,
    modifier: Modifier = Modifier
) {
    var isDialogOpened by remember { mutableStateOf(false) }

    if (isDialogOpened) {
        ReviewDialog(
            viewModel = viewModel,
            onDismissRequest = {
                isDialogOpened = false
            },
            onRatingChanged = viewModel::setRating
        )
    }

    LazyColumn(
        modifier = Modifier
            .background(Gray900)
            .then(
                if (isDialogOpened) {
                    modifier.blur(15.dp)
                } else {
                    modifier
                }
            )
    ) {
        item {
            MovieImage(posterLink = movie.poster)

            Spacer(modifier = Modifier.height(Padding20))
        }

        item {
            MovieInfo(
                viewModel = viewModel,
                name = movie.name,
                rating = movie.rating,
                isInFavorites = movie.isInFavorites,
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
                            .noRippleClickable {
                                isDialogOpened = !isDialogOpened
                            }
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
                        isDialogOpened = !isDialogOpened
                    },
                    onDeleteClick = viewModel::deleteReview,
                    shimmerStartOffsetX = shimmerStartOffsetX
                )

                Spacer(modifier = Modifier.height(Padding20))
            }
        }

        items(count = movie.usersReviews.size) { reviewIndex ->
            val review = movie.usersReviews[reviewIndex]

            MovieReviewItem(
                review = review,
                shimmerStartOffsetX = shimmerStartOffsetX,
                modifier = modifier.padding(horizontal = PaddingMedium)
            )

            Spacer(modifier = Modifier.height(Padding20))
        }
    }
}