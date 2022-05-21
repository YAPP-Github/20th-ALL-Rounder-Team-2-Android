package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

private val BUTTON_RADIUS = 10.dp
private val BUTTON_HEIGHT = 56.dp

@Composable
fun KnowllyFilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = KnowllyTheme.colors.primaryDark,
        contentColor = KnowllyTheme.colors.grayFF,
        disabledContainerColor = KnowllyTheme.colors.grayDD,
        disabledContentColor = KnowllyTheme.colors.grayFF
    )

    Button(
        modifier = modifier.height(BUTTON_HEIGHT),
        shape = RoundedCornerShape(BUTTON_RADIUS),
        onClick = onClick,
        enabled = enabled,
        colors = buttonColors
    ) {
        Text(
            text = text,
            style = KnowllyTheme.typography.button1,
        )
    }
}

@Composable
fun KnowllyOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = ButtonDefaults.outlinedButtonColors(
        contentColor = KnowllyTheme.colors.primaryDark,
    )
    val border = ButtonDefaults.outlinedButtonBorder.copy(
        brush = SolidColor(KnowllyTheme.colors.primaryDark)
    )
    OutlinedButton(
        modifier = modifier.height(BUTTON_HEIGHT),
        shape = RoundedCornerShape(BUTTON_RADIUS),
        colors = colors,
        border = border,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = KnowllyTheme.typography.button1,
        )
    }
}

@Preview("Enabled")
@Composable
fun KnowllyFilledButtonPreviewEnabled() {
    KnowllyTheme {
        KnowllyFilledButton(
            text = "버튼",
            onClick = { },
            enabled = true,
        )
    }
}

@Preview("Disabled")
@Composable
fun KnowllyFilledButtonPreviewDisabled() {
    KnowllyTheme {
        KnowllyFilledButton(
            text = "버튼",
            onClick = { },
            enabled = false,
        )
    }
}

@Preview
@Composable
fun KnowllyOutlinedButtonPreview() {
    KnowllyTheme {
        KnowllyOutlinedButton(
            text = "버튼",
            onClick = { },
        )
    }
}
