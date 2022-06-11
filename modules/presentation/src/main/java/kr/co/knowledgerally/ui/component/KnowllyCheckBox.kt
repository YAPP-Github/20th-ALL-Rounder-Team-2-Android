package kr.co.knowledgerally.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@OptIn(ExperimentalMaterial3Api::class)
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
fun KnowllyCheckBoxTile(
    checked: Boolean,
    onCheckedChanged: ((Boolean) -> Unit)?,
    text: String,
    actionIcon: (@Composable () -> Unit)? = null,
    onActionTap: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        KnowllyCheckBox(checked = checked, onCheckedChanged = onCheckedChanged)
        Text(
            text = text,
            style = KnowllyTheme.typography.subtitle4
        )
        if (actionIcon != null && onActionTap != null) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = onActionTap, content = actionIcon)
        }
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
