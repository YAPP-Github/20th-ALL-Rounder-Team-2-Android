package kr.co.knowledgerally.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(GetContent()) { uri ->
        if (uri != null) {
            imageUri = uri
        }
    }

    val nameState by viewModel.name.collectAsState()
    val introductionState by viewModel.introduction.collectAsState()
    val canUpload by viewModel.canUpload.collectAsState()

    ProfileScreen(
        imageUri = imageUri,
        onImageClick = { launcher.launch("image/*") },
        nameState = nameState,
        onNameChange = viewModel::updateName,
        introductionState = introductionState,
        onIntroductionChange = viewModel::updateIntroduction,
        canUpload = canUpload,
        onUpload = viewModel::uploadProfile,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    onImageClick: () -> Unit,
    nameState: TextUiState,
    onNameChange: (String) -> Unit,
    introductionState: TextUiState,
    onIntroductionChange: (String) -> Unit,
    canUpload: Boolean,
    onUpload: () -> Unit,
) {
    Box(modifier = modifier.padding(horizontal = 24.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 64.dp)
        ) {
            ProfileTitle(text = stringResource(id = R.string.profile_title))

            ProfileImage(
                imageUri = imageUri,
                onClick = onImageClick,
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 16.dp)
                    .size(108.dp)
                    .align(Alignment.CenterHorizontally),
            )

            ProfileSubtitle(text = stringResource(id = R.string.profile_name))
            ProfileTextField(
                value = nameState.text,
                onValueChange = onNameChange,
                maxLength = nameState.maxLength,
                placeholder = stringResource(id = R.string.profile_name_hint),
                singleLine = true,
                helperText = "message",
            )

            VerticalSpacer(height = 20.dp)

            ProfileSubtitle(text = stringResource(id = R.string.profile_introduction))
            ProfileTextField(
                value = introductionState.text,
                onValueChange = onIntroductionChange,
                maxLength = introductionState.maxLength,
                placeholder = stringResource(id = R.string.profile_introduction_hint),
                singleLine = false,
                minHeight = 180.dp,
            )

            // Button Size + Button Padding
            VerticalSpacer(height = 90.dp)
        }

        ProfileUploadButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onUpload,
            enabled = canUpload
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
    imageUri: Uri? = null,
    onClick: () -> Unit,
) {
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
                    .clickable { onClick() })
            {
                Image(
                    painter = painterResource(id = R.drawable.img_profile_placeholder),
                    contentDescription = null
                )
                AsyncImage(
                    model = imageUri,
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
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    placeholder: String,
    singleLine: Boolean,
    modifier: Modifier = Modifier,
    helperText: String = "",
    minHeight: Dp = Dp.Unspecified,
) {
    KnowllyTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.padding(top = 12.dp),
        placeholder = placeholder,
        singleLine = singleLine,
        helperText = helperText,
        helperTextEnabled = helperText.isNotBlank(),
        counterMaxLength = maxLength,
        counterEnabled = true,
        minHeight = minHeight
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
        ProfileScreen(
            imageUri = null,
            onImageClick = { },
            nameState = TextUiState.default(10),
            onNameChange = { },
            introductionState = TextUiState.default(100),
            onIntroductionChange = { },
            canUpload = true,
            onUpload = { },
        )
    }
}
