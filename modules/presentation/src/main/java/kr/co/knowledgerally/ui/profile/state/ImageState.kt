package kr.co.knowledgerally.ui.profile.state

import android.net.Uri
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class ImageState {
    var uri by mutableStateOf<Uri>(Uri.EMPTY)
    val isEmpty: Boolean
        get() = uri == Uri.EMPTY

    val uriString: String
        get() = if (isEmpty) "" else uri.toString()
}
