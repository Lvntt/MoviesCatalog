package ru.lantt.moviescatalog.presentation.ui.screen.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.lantt.moviescatalog.presentation.ui.theme.Accent
import ru.lantt.moviescatalog.presentation.ui.theme.Gray200
import ru.lantt.moviescatalog.presentation.ui.theme.Label_M_14
import ru.lantt.moviescatalog.presentation.ui.theme.Padding16
import ru.lantt.moviescatalog.presentation.ui.util.noRippleClickable

@Composable
fun AuthBottomBar(
    descriptionText: String,
    functionalText: String,
    onFunctionalTextClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row {
                Text(
                    text = "$descriptionText ",
                    style = Label_M_14,
                    color = Gray200
                )

                Text(
                    modifier = Modifier.noRippleClickable {
                        onFunctionalTextClick()
                    },
                    text = functionalText,
                    style = Label_M_14,
                    color = Accent
                )
            }
        }

        Spacer(modifier = Modifier.height(Padding16))
    }
}