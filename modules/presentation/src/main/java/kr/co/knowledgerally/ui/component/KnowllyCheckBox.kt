package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun KnowllyCheckBox(
    checked: Boolean,
    onCheckedChanged: ((Boolean) -> Unit)?,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChanged,
        colors = CheckboxDefaults.colors(
            checkedColor = KnowllyTheme.colors.primaryDark,
            uncheckedColor = KnowllyTheme.colors.grayCC
        )
    )
}

@Composable
fun KnowllyCheckBoxText(
    checked: Boolean,
    onCheckedChanged: ((Boolean) -> Unit)?,
    text: String,
    actionIcon: (@Composable () -> Unit) = { Box(modifier = Modifier.size(20.dp)) },
    onActionTap: (() -> Unit) = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            KnowllyCheckBox(
                checked = checked,
                onCheckedChanged = onCheckedChanged
            )
        }
        HorizontalSpacer(width = 8.dp)
        Text(
            text = text,
            style = KnowllyTheme.typography.subtitle4
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onActionTap, content = actionIcon)
        HorizontalSpacer(width = 8.dp)
    }
}

@Preview("Checked")
@Composable
private fun KnowllyCheckBoxPreviewChecked() {
    KnowllyTheme {
        KnowllyCheckBox(checked = true, onCheckedChanged = {})
    }
}

@Preview("Unchecked")
@Composable
private fun KnowllyCheckBoxPreviewUnchecked() {
    KnowllyTheme {
        KnowllyCheckBox(checked = false, onCheckedChanged = {})
    }
}

@Preview
@Composable
private fun KnowllyCheckBoxTextPreview() {
    KnowllyTheme {
        KnowllyCheckBoxText(
            checked = true,
            onCheckedChanged = { },
            text = "KnowllyCheckBoxTextPreview",
            actionIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            onActionTap = { }
        )
    }
}