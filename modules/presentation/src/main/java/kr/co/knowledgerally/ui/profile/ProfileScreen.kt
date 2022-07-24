package kr.co.knowledgerally.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyMultilineTextField
import kr.co.knowledgerally.ui.component.KnowllySinglelineTextField
import kr.co.knowledgerally.ui.component.Loading
import kr.co.knowledgerally.ui.profile.state.ImageState
import kr.co.knowledgerally.ui.profile.state.IntroductionState
import kr.co.knowledgerally.ui.profile.state.KakaoIdState
import kr.co.knowledgerally.ui.profile.state.Mode
import kr.co.knowledgerally.ui.profile.state.NameState
import kr.co.knowledgerally.ui.profile.state.OnboardResult
import kr.co.knowledgerally.ui.profile.state.PortfolioState
import kr.co.knowledgerally.ui.profile.state.ProfileState
import kr.co.knowledgerally.ui.profile.state.rememberProfileState
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onResult: (OnboardResult) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val user by viewModel.user.collectAsState()
    val profileState = user?.let {
        rememberProfileState(
            nameState = NameState(user!!.profile.username),
            introductionState = IntroductionState(user!!.profile.introduction),
            kakaoIdState = KakaoIdState(user!!.profile.kakaoId),
            portfolioState = PortfolioState(user!!.profile.portfolio),
            imageState = remember { ImageState(Uri.parse(user!!.profile.imageUrl ?: "")) }
        )
    }
        ?: rememberProfileState()

    Box(modifier = Modifier.fillMaxSize()) {
        ProfileContent(
            profileState = profileState,
            onSubmit = {
                viewModel.submit(
                    name = profileState.nameState.text,
                    introduction = profileState.introductionState.text,
                    kakaoId = profileState.kakaoIdState.text,
                    portfolio = profileState.portfolioState.text,
                    imageUri = profileState.imageState.uriString,
                )
            },
            mode = viewModel.mode
        )

        if (uiState.isLoading) {
            Loading()
        }
    }

    uiState.result?.let { result ->
        LaunchedEffect(result) { onResult(result) }
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    profileState: ProfileState,
    onSubmit: () -> Unit,
    mode: Mode
) {
    val titleResId = when (mode) {
        Mode.New -> R.string.profile_title
        Mode.Edit -> R.string.profile_title_edit
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, top = 68.dp, end = 24.dp, bottom = 120.dp)
        ) {
            ProfileTitle(text = stringResource(id = titleResId))

            // 프로필 사진
            ProfileImage(
                state = profileState.imageState,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // 이름
            ProfileName(
                state = profileState.nameState,
                modifier = Modifier.padding(top = 48.dp)
            )

            // 자기소개
            ProfileIntroduction(
                state = profileState.introductionState,
                modifier = Modifier.padding(top = 24.dp)
            )

            // 카카오톡 ID
            ProfileKakaoId(
                state = profileState.kakaoIdState,
                modifier = Modifier.padding(top = 24.dp)
            )

            // 포트폴리오
            ProfilePortfolio(
                state = profileState.portfolioState,
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        ProfileButton(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            onClick = onSubmit,
            enabled = profileState.isValid
        )
    }
}

@Composable
private fun ProfileTitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.headline3)
}

@Composable
private fun ProfileSubtitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.subtitle4)
}

@Composable
private fun ProfileDescription(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = KnowllyTheme.typography.body2,
        color = KnowllyTheme.colors.gray8F
    )
}

@Composable
private fun ProfileImage(
    state: ImageState,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                Logger.d("Profile", "uri: $uri")
                state.uri = uri
            }
        },
    )
    val openGallery: () -> Unit = { launcher.launch("image/*") }
    Box(
        modifier = modifier.size(108.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = KnowllyTheme.colors.grayCC,
            shape = CircleShape,
        ) {
            Box(
                modifier = Modifier
                    .size(108.dp)
                    .clickable { showDialog = true })
            {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_placeholder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                AsyncImage(
                    model = state.uri,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Surface(
            shape = CircleShape,
            color = KnowllyTheme.colors.gray00,
            border = BorderStroke(1.dp, KnowllyTheme.colors.grayFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier.clickable { showDialog = true },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_a_photo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = KnowllyTheme.colors.grayFF
                )
            }
        }
    }

    val dismiss = { showDialog = false }
    ImagePickerDialog(
        isVisible = showDialog,
        actions = ImageActions(
            onDismiss = dismiss,
            onDefault = {
                state.uri = Uri.EMPTY
                dismiss()
            },
            onPick = {
                openGallery()
                dismiss()
            }
        ),
    )
}

@Composable
private fun ProfileName(
    state: NameState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ProfileSubtitle(text = stringResource(id = R.string.profile_name))
        KnowllySinglelineTextField(
            value = state.text,
            onValueChange = { state.text = it },
            modifier = Modifier
                .padding(top = 8.dp)
                .onFocusChanged { state.onFocusChange(it.isFocused) },
            placeholder = stringResource(id = R.string.profile_name_hint),
            isError = state.isError,
            counterMaxLength = NameState.MAX_LENGTH,
            counterEnabled = true,
        )
    }
}

@Composable
private fun ProfileIntroduction(
    state: IntroductionState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ProfileSubtitle(text = stringResource(id = R.string.profile_introduction))
        KnowllyMultilineTextField(
            value = state.text,
            onValueChange = { state.text = it },
            modifier = Modifier
                .padding(top = 8.dp)
                .onFocusChanged { state.onFocusChange(it.isFocused) },
            placeholder = stringResource(id = R.string.profile_introduction_hint),
            isError = state.isError,
            counterMaxLength = IntroductionState.MAX_LENGTH,
            counterEnabled = true,
            minHeight = 180.dp,
        )
    }
}

@Composable
private fun ProfileKakaoId(
    state: KakaoIdState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ProfileSubtitle(text = stringResource(id = R.string.profile_kakao_id))
        ProfileDescription(
            text = stringResource(id = R.string.profile_kakao_id_description),
            modifier = Modifier.padding(top = 2.dp),
        )
        KnowllySinglelineTextField(
            value = state.text,
            onValueChange = { state.text = it },
            modifier = Modifier
                .padding(top = 8.dp)
                .onFocusChanged { state.onFocusChange(it.isFocused) },
            placeholder = stringResource(id = R.string.profile_kakao_id_hint),
        )
    }
}

@Composable
private fun ProfilePortfolio(
    state: PortfolioState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            ProfileSubtitle(text = stringResource(id = R.string.profile_portfolio))
            Text(
                text = stringResource(id = R.string.profile_portfolio_option),
                modifier = Modifier.padding(start = 4.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray44
            )
        }
        KnowllySinglelineTextField(
            value = state.text,
            onValueChange = { state.text = it },
            modifier = Modifier
                .padding(top = 8.dp)
                .onFocusChanged { state.onFocusChange(it.isFocused) },
            placeholder = stringResource(id = R.string.profile_portfolio_hint),
        )
    }
}

@Composable
private fun ProfileButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    KnowllyContainedButton(
        text = stringResource(id = R.string.profile_upload),
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    KnowllyTheme {
        ProfileContent(
            profileState = rememberProfileState(),
            onSubmit = { },
            mode = Mode.New
        )
    }
}
