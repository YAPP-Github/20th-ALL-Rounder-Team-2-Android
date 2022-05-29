package kr.co.knowledgerally.ui.profile

data class TextUiState(
    val text: String,
    val maxLength: Int,
    val isError: Boolean,
) {

    val isNotBlank: Boolean = text.isNotBlank()

    fun update(text: String): TextUiState = copy(text = text.take(maxLength))

    companion object {
        fun default(maxLength: Int): TextUiState = TextUiState("", maxLength = maxLength, false)
    }
}
