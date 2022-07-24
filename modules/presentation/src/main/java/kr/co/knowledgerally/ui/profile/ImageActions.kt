package kr.co.knowledgerally.ui.profile

class ImageActions(
    val onDismiss: () -> Unit = { },
    val onPick: () -> Unit = {},
    val onDefault: () -> Unit = { },
) {
    companion object {
        val Default: ImageActions = ImageActions()
    }
}
