package kr.co.knowledgerally.ui.register.info

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kr.co.knowledgerally.domain.model.Category
import kr.co.knowledgerally.domain.model.Registration
import kr.co.knowledgerally.model.CategoryModel

@Stable
class RegisterInfoState(
    category: CategoryModel? = null,
    name: String = "",
    introduce: String = "",
    tags: Set<String> = emptySet(),
    imageUris: List<Uri> = emptyList(),
) {
    var category: CategoryModel? by mutableStateOf(category)
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

        val Saver = listSaver<RegisterInfoState, Any>(
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
                RegisterInfoState(
                    category = CategoryModel.from(it[0] as Int),
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
fun rememberRegisterState() = rememberSaveable(stateSaver = RegisterInfoState.Saver) {
    mutableStateOf(RegisterInfoState())
}

fun RegisterInfoState.toRegistration() = Registration(
    category = when (category) {
        CategoryModel.PM -> Category.PM
        CategoryModel.Design -> Category.Design
        CategoryModel.Develop -> Category.Develop
        CategoryModel.Marketing -> Category.Marketing
        CategoryModel.Language -> Category.Language
        CategoryModel.Etc -> Category.Etc
        else -> throw IllegalStateException()
    },
    topic = name,
    introduce = introduce,
    tags = tags.toList(),
    imageUris = imageUris.map { it.toString() }
)
