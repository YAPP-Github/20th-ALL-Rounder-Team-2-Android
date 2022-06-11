package kr.co.knowledgerally.ui.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTextField
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val profileState = rememberProfileState()

    ProfileContent(
        profileState = profileState,
        onSubmit = {
            viewModel.submitProfile(
                name = profileState.nameState.text,
                introduction = profileState.introductionState.text,
                imageUri = profileState.imageState.uri.toString()
            )
        }
    )
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    profileState: ProfileState,
    onSubmit: () -> Unit,
) {
    val nameState = profileState.nameState
    val introductionState = profileState.introductionState
    val imageState = profileState.imageState

    Box(modifier = modifier.padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 64.dp)
        ) {
            ProfileTitle(text = stringResource(id = R.string.profile_title))

            ProfileImage(
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 16.dp)
                    .size(108.dp)
                    .align(Alignment.CenterHorizontally),
                imageState = imageState,
            )

            ProfileSubtitle(text = stringResource(id = R.string.profile_name))
            ProfileTextField(
                state = nameState,
                maxLength = NameState.MAX_LENGTH,
                placeholder = stringResource(id = R.string.profile_name_hint),
                helperText = stringResource(id = R.string.profile_name_helper_text),
                singleLine = true,
            )

            VerticalSpacer(height = 20.dp)

            ProfileSubtitle(text = stringResource(id = R.string.profile_introduction))
            ProfileTextField(
                state = introductionState,
                maxLength = IntroductionState.MAX_LENGTH,
                placeholder = stringResource(id = R.string.profile_introduction_hint),
                helperText = stringResource(id = R.string.profile_introduction_helper_text),
                singleLine = false,
                minHeight = 180.dp,
            )

            // Button Size + Button Padding
            VerticalSpacer(height = 90.dp)
        }

        ProfileUploadButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onSubmit,
            enabled = profileState.isValid
        )
    }
}

@Composable
private fun ProfileTitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.headline4)
}

@Composable
private fun ProfileSubtitle(text: String) {
    Text(text = text, style = KnowllyTheme.typography.subtitle1)
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    imageState: ImageState,
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                imageState.uri = uri
            }
        },
    )

    Box(
        modifier = modifier,
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
                    .clickable { launcher.launch("image/*") })
            {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_placeholder),
                    contentDescription = null
                )
                AsyncImage(
                    model = imageState.uri,
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
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_a_photo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = KnowllyTheme.colors.grayFF
                )
            }
        }
    }
}

@Composable
private fun ProfileTextField(
    state: TextFieldState,
    maxLength: Int,
    placeholder: String,
    singleLine: Boolean,
    helperText: String,
    modifier: Modifier = Modifier,
    minHeight: Dp = Dp.Unspecified,
) {
    val isError = state.isError
    KnowllyTextField(
        value = state.text,
        onValueChange = { state.text = it },
        modifier = modifier
            .padding(top = 12.dp)
            .onFocusChanged { state.onFocusChange(it.isFocused) },
        placeholder = placeholder,
        singleLine = singleLine,
        isError = isError,
        helperText = helperText,
        helperTextEnabled = isError,
        counterMaxLength = maxLength,
        counterEnabled = true,
        minHeight = minHeight,
    )
}

@Composable
private fun ProfileUploadButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    KnowllyContainedButton(
        text = stringResource(id = R.string.profile_upload),
        onClick = onClick,
        modifier = modifier
            .padding(bottom = 24.dp, top = 20.dp)
            .fillMaxWidth(),
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
        )
    }
}
