package kr.co.knowledgerally.ui.main.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun WelcomeDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        WelcomeDialogContent(onDismiss)
    }
}

@Composable
private fun WelcomeDialogContent(onDismiss: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 9.dp,
        color = KnowllyTheme.colors.grayFF,
        contentColor = Color.Unspecified,
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.dialog_welcome_title),
                modifier = Modifier.padding(start = 28.dp, top = 32.dp),
                style = KnowllyTheme.typography.headline4,
            )
            VerticalSpacer(height = 36.dp)
            Image(
                painter = painterResource(id = R.drawable.img_welcome),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(KnowllyTheme.colors.secondaryLimeLight),
                contentAlignment = Alignment.Center,
            ) {
                KnowllyContainedButton(
                    text = stringResource(id = R.string.dialog_welcome_button),
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .width(156.dp)
                )
            }
        }
    }
}

@Preview(widthDp = 300)
@Composable
private fun WelcomeDialogPreview() {
    KnowllyTheme {
        WelcomeDialogContent(onDismiss = { })
    }
}
