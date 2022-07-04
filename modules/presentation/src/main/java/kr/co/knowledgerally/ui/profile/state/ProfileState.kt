package kr.co.knowledgerally.ui.profile.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class ProfileState(
    val nameState: NameState,
    val introductionState: IntroductionState,
    val imageState: ImageState,
    val kakaoIdState: KakaoIdState,
    val portfolioState: PortfolioState,
) {

    val isValid: Boolean
        get() = nameState.isValid && introductionState.isValid && kakaoIdState.isValid
}

@Composable
fun rememberProfileState(
    nameState: NameState = rememberNameState(),
    introductionState: IntroductionState = IntroductionState(),
    kakaoIdState: KakaoIdState = rememberKakaoIdState(),
    portfolioState: PortfolioState = PortfolioState(),
    imageState: ImageState = remember { ImageState() }
) = remember {
    ProfileState(
        nameState = nameState,
        introductionState = introductionState,
        imageState = imageState,
        kakaoIdState = kakaoIdState,
        portfolioState = portfolioState
    )
}
