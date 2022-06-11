package kr.co.knowledgerally.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class TextFieldState(
    initialText: String,
    private val validator: (String) -> Boolean = { true },
) {
    var text by mutableStateOf(initialText)
    private var isFocusedDirty: Boolean by mutableStateOf(false)
    private var isFocused: Boolean by mutableStateOf(false)

    val isValid: Boolean
        get() = validator(text)

    val isError: Boolean
        get() = isFocusedDirty && !isValid

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) {
            isFocusedDirty = true
        }
    }
}

