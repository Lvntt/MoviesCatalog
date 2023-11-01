package ru.lantt.moviescatalog.presentation.ui.screen.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.lantt.moviescatalog.R
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray900
import ru.lantt.moviescatalog.presentation.ui.theme.Label_SB_15
import ru.lantt.moviescatalog.presentation.ui.theme.Padding12
import ru.lantt.moviescatalog.presentation.ui.theme.Title_B_24
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun ProfileAvatar(
    nickname: String?,
    avatarLink: String?,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().background(Gray900),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(88.dp)
                .background(
                    color = Color(0xFFEBEDF0),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (avatarLink != null) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(avatarLink)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(88.dp)
                )
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.profile_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        if (nickname != null) {
            Text(
                text = nickname,
                style = Title_B_24,
                color = Color.White
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    onLogoutClick()
                }
        ) {
            Text(
                text = stringResource(id = R.string.sign_out_of_account),
                style = Label_SB_15,
                color = Accent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Padding12),
                textAlign = TextAlign.Center
            )
        }
    }
}