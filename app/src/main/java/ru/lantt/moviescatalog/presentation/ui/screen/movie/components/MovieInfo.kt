package ru.lantt.moviescatalog.presentation.ui.screen.movie.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray750
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24
import ru.lantt.moviescatalog.presentation.ui.util.getRatingColor
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable
import ru.lantt.moviescatalog.presentation.viewmodel.movie.MovieViewModel

@Composable
fun MovieInfo(
    viewModel: MovieViewModel,
    isInFavorites: Boolean,
    name: String?,
    rating: Double?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (rating != null) {
            val ratingColor = getRatingColor(rating)

            MovieRating(
                ratingColor = ratingColor,
                rating = rating,
                modifier = Modifier.padding(end = PaddingSmall)
            )
        }

        if (name != null) {
            Text(
                text = name,
                style = Title_B_24,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .padding(start = PaddingSmall)
                .background(color = Gray750, shape = CircleShape)
                .noRippleClickable {
                    if (isInFavorites) {
                        viewModel.deleteFavoriteMovie()
                        Toast.makeText(
                            context,
                            context.getString(R.string.removed_from_favorites),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        viewModel.addFavoriteMovie()
                        Toast.makeText(
                            context,
                            context.getString(R.string.added_to_favorites),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id =
                    if (isInFavorites) R.drawable.heart_filled_icon
                    else R.drawable.heart_outlined_icon
                ),
                contentDescription = stringResource(id = R.string.add_to_favorites),
                modifier = Modifier.padding(PaddingSmall),
                tint = if (isInFavorites) Accent else Color.White
            )
        }
    }
}