package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import ru.lantt.moviescatalog.domain.entity.UserShort
import ru.lantt.moviescatalog.presentation.ui.screen.main.components.shimmer.ShimmerBox
import ru.lantt.moviescatalog.presentation.ui.screen.movie.components.PlaceholderAvatar
import ru.lantt.moviescatalog.presentation.ui.theme.SmallAvatarSize

@Composable
fun MovieReviewItemAvatar(
    author: UserShort?,
    isAnonymous: Boolean,
    shimmerStartOffsetXProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(SmallAvatarSize)
            .clip(CircleShape)
    ) {
        if (author?.avatar == null || isAnonymous) {
            PlaceholderAvatar()
        } else {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(author.avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        ShimmerBox(
                            width = 40.dp,
                            height = 40.dp,
                            shimmerStartOffsetXProvider = shimmerStartOffsetXProvider,
                            modifier = Modifier.clip(CircleShape)
                        )
                    }

                    is AsyncImagePainter.State.Error -> {
                        PlaceholderAvatar()
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
        }
    }
}