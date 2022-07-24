package kr.co.knowledgerally.ui.profile.state

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import kr.co.knowledgerally.domain.model.User

@Stable
class ProfileState(
    val nameState: NameState,
    val introductionState: IntroductionState,
    val imageState: ImageState,
    val kakaoIdState: KakaoIdState,
    val portfolioState: PortfolioState,
    private val user: User? = null,
) {

    private val hasChanged: Boolean
        get() {
            if (user == null) return true

            val profile = user.profile
            return nameState.text != profile.username
                || introductionState.text != profile.introduction
                || imageState.uri != (profile.imageUrl?.let(Uri::parse) ?: Uri.EMPTY)
                || kakaoIdState.text != profile.kakaoId
                || portfolioState.text != profile.portfolio
        }

    val isValid: Boolean
        get() = nameState.isValid && introductionState.isValid && kakaoIdState.isValid && hasChanged
}

@Composable
fun rememberProfileState(user: User?) = remember(user != null) {
    if (user == null) {
        ProfileState(
            nameState = NameState(""),
            introductionState = IntroductionState(),
            imageState = ImageState(),
            kakaoIdState = KakaoIdState(""),
            portfolioState = PortfolioState(),
        )
    } else {
        val profile = user.profile
        ProfileState(
            nameState = NameState(profile.username),
            introductionState = IntroductionState(profile.introduction),
            imageState = ImageState(profile.imageUrl),
            kakaoIdState = KakaoIdState(profile.kakaoId),
            portfolioState = PortfolioState(profile.portfolio),
            user = user,
        )
    }
}
