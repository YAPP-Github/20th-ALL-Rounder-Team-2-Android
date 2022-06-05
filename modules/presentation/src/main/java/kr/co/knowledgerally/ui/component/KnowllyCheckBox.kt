package kr.co.knowledgerally.ui.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
        colors = CheckboxDefaults.colors(checkedColor = KnowllyTheme.colors.primaryDark)
    )
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