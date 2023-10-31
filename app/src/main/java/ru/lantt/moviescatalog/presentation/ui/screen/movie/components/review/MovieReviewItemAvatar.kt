package ru.lantt.moviescatalog.presentation.ui.screen.movie.components.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.domain.entity.UserShort
import ru.lantt.moviescatalog.presentation.ui.theme.PaddingSmall
import ru.lantt.moviescatalog.presentation.ui.theme.SmallAvatarSize

@Composable
fun MovieReviewItemAvatar(
    author: UserShort?,
    isAnonymous: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(SmallAvatarSize)
            .clip(CircleShape)
    ) {
        if (author?.avatar == null || isAnonymous) {
            Box(
                modifier = modifier
                    .size(SmallAvatarSize)
                    .background(
                        color = Color(0xFFEBEDF0),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.profile_icon),
                    modifier = Modifier
                        .padding(PaddingSmall),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        } else {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(author.avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}