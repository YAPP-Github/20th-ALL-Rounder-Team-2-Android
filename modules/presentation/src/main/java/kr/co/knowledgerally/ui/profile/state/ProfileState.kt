package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class ProfileState(
    val nameState: NameState,
    val introductionState: IntroductionState,
    val imageState: ImageState,
) {

    val isValid: Boolean
        get() = nameState.isValid && introductionState.isValid
}

@Composable
fun rememberProfileState(
    nameState: NameState = rememberNameState(),
    introductionState: IntroductionState = IntroductionState(),
    imageState: ImageState = remember { ImageState() }
) = remember {
    ProfileState(nameState, introductionState, imageState)
}
