package kr.co.knowledgerally.ui.register

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Stable
class RegisterState(
    category: CategoryItem? = null,
    name: String = "",
    introduce: String = "",
    tags: Set<String> = emptySet(),
    imageUris: List<Uri> = emptyList(),
) {
    var category: CategoryItem? by mutableStateOf(category)
    var name: String by mutableStateOf(name)
    var introduce: String by mutableStateOf(introduce)

    var tags: Set<String> by mutableStateOf(tags)
        private set

    var imageUris: List<Uri> by mutableStateOf(imageUris)
        private set

    val canNext by derivedStateOf {
        this.category != null && this.name.isNotBlank() && this.introduce.isNotBlank()
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

        val Saver = listSaver<RegisterState, Any>(
            save = {
                listOf(
                    it.category?.ordinal ?: 0,
                    it.name,
                    it.introduce,
                    it.tags,
                    it.imageUris
                )
            },
            restore = {
                RegisterState(
                    category = CategoryItem.from(it[0] as Int),
                    name = it[1] as String,
                    introduce = it[2] as String,
                    tags = it[3] as Set<String>,
                    imageUris = it[4] as List<Uri>,
                )
            }
        )
    }
}

@Composable
fun rememberRegisterState() = rememberSaveable(stateSaver = RegisterState.Saver) {
    mutableStateOf(RegisterState())
}
