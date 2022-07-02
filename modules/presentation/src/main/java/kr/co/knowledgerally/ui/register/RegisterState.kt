package kr.co.knowledgerally.ui.register

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class RegisterState {
    var category: CategoryItem? by mutableStateOf(null)
    var name: String by mutableStateOf("")
    var introduce: String by mutableStateOf("")

    var tags: Set<String> by mutableStateOf(emptySet())
        private set

    var imageUris: List<Uri> by mutableStateOf(emptyList())
        private set

    val canNext by derivedStateOf {
        category != null && name.isNotBlank() && introduce.isNotBlank()
    }

    fun addTag(tag: String) {
        tags = tags + tag
    }

    fun removeTag(tag: String) {
        tags = tags.filterNot { it == tag }.toSet()
    }

    fun updateImageUris(uris: List<Uri>) {
        imageUris = uris.take(MAX_IMAGE_LENGTH)
    }

    fun removeImageUri(uri: Uri) {
        imageUris = imageUris.filterNot { it == uri }
    }

    companion object {
        private const val MAX_IMAGE_LENGTH = 5
    }
}

@Composable
fun rememberRegisterState() = remember { RegisterState() }
