package kr.co.knowledgerally.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class CheckState {
    var isChecked by mutableStateOf(false)

    fun toggle() {
        isChecked = isChecked.not()
    }
}

@Composable
fun rememberCheckState() = remember { CheckState() }
