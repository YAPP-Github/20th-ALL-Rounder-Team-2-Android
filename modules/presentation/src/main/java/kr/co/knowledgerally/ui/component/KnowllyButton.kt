package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyContainedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = KnowllyButtonDefaults.textStyle,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(KnowllyButtonDefaults.ButtonHeight),
        enabled = enabled,
        shape = KnowllyButtonDefaults.ButtonShape,
        colors = KnowllyButtonDefaults.containedButtonColors,
        contentPadding = contentPadding,
    ) {
        Text(text = text, style = textStyle)
    }
}

@Composable
fun KnowllyOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = KnowllyButtonDefaults.textStyle,
    contentPadding: PaddingValues = PaddingValues(horizontal = 20.dp)
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(KnowllyButtonDefaults.ButtonHeight),
        shape = KnowllyButtonDefaults.ButtonShape,
        border = KnowllyButtonDefaults.outlinedButtonBorder,
        colors = KnowllyButtonDefaults.outlinedButtonColors,
        contentPadding = contentPadding,
    ) {
        Text(text = text, style = textStyle)
    }
}

object KnowllyButtonDefaults {
    val ButtonHeight = 56.dp
    val ButtonShape = RoundedCornerShape(10.dp)
    val textStyle
        @Composable
        get() = KnowllyTheme.typography.button1

    val containedButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = KnowllyTheme.colors.primary,
            contentColor = KnowllyTheme.colors.grayFF,
            disabledContainerColor = KnowllyTheme.colors.grayDD,
            disabledContentColor = KnowllyTheme.colors.grayFF
        )

    val outlinedButtonColors
        @Composable
        get() = ButtonDefaults.outlinedButtonColors(
            contentColor = KnowllyTheme.colors.primaryDark,
        )

    val outlinedButtonBorder
        @Composable
        get() = ButtonDefaults.outlinedButtonBorder.copy(
            brush = SolidColor(KnowllyTheme.colors.primaryDark)
        )
}

@Preview("Enabled")
@Composable
private fun KnowllyContainedButtonPreviewEnabled() {
    KnowllyTheme {
        KnowllyContainedButton(
            text = "버튼",
            onClick = { },
            enabled = true,
        )
    }
}

@Preview("Disabled")
@Composable
private fun KnowllyContainedButtonPreviewDisabled() {
    KnowllyTheme {
        KnowllyContainedButton(
            text = "버튼",
            onClick = { },
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun KnowllyOutlinedButtonPreview() {
    KnowllyTheme {
        KnowllyOutlinedButton(
            text = "버튼",
            onClick = { },
        )
    }
}
