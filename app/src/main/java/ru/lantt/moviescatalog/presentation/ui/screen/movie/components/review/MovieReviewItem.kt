package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lantt.moviescatalog.data.datasource.MockDetailedMovieSource
import ru.lantt.moviescatalog.domain.entity.Review
import ru.lantt.moviescatalog.presentation.ui.theme.Gray400
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_12
import ru.lantt.moviescatalog.presentation.ui.theme.MovieRegularPadding
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Text_R_14
import ru.lantt.moviescatalog.presentation.ui.util.getFormattedDateTime

@Composable
fun MovieReviewItem(
    review: Review,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieReviewItemAvatar(
                author = review.author,
                isAnonymous = review.isAnonymous
            )

            Spacer(modifier = Modifier.width(MovieRegularPadding))

            MovieReviewItemInfo(
                nickname = review.author?.nickName,
                rating = review.rating,
                isAnonymous = review.isAnonymous
            )
        }

        Spacer(modifier = Modifier.height(PaddingSmall))

        if (review.reviewText != null) {
            Text(
                text = review.reviewText,
                style = Text_R_14,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = getFormattedDateTime(review.createDateTime),
            style = Label_M_12,
            color = Gray400
        )
    }
}

@Preview
@Composable
fun MovieReviewItemPreview() {
    MovieReviewItem(
        review = MockDetailedMovieSource.movie.usersReviews[1],
        modifier = Modifier.background(Gray900)
    )
}