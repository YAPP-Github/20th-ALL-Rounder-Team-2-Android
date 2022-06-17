package kr.co.knowledgerally.ui.applicant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyLoading
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ApplicantScreen(
    viewModel: ApplicantViewModel,
    navigateUp: () -> Unit,
    navigateToForm: (Applicant) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        ApplicantContent(
            uiState = uiState,
            onNavigationClick = navigateUp,
            navigateToForm = navigateToForm,
        )
    }
}

@Composable
fun ApplicantContent(
    uiState: ApplicantUiState,
    onNavigationClick: () -> Unit,
    navigateToForm: (Applicant) -> Unit,
) {
    Column {
        KnowllyTopAppBar(onNavigationClick = onNavigationClick)
        ApplicantTitle()
        ApplicantSubtitle()

        VerticalSpacer(height = 16.dp)
        if (uiState is ApplicantUiState.Applicants) {
            ApplicantItems(uiState.applicants, navigateToForm)
        }
    }

    if (uiState is ApplicantUiState.Loading) {
        KnowllyLoading()
    }
}

@Composable
private fun ApplicantTitle() {
    Text(
        text = stringResource(id = R.string.applicant_title),
        style = KnowllyTheme.typography.headline3,
        modifier = Modifier.padding(start = 24.dp, top = 12.dp)
    )
}

@Composable
private fun ApplicantSubtitle() {
    Text(
        text = stringResource(id = R.string.applicant_description),
        modifier = Modifier
            .padding(start = 24.dp, top = 4.dp, end = 24.dp)
            .fillMaxWidth(),
        style = KnowllyTheme.typography.body1,
        color = KnowllyTheme.colors.gray8F,
    )
}

@Composable
private fun ApplicantItems(
    applicants: List<Applicant>,
    onClick: (Applicant) -> Unit,
) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 18.dp)) {
        items(
            items = applicants,
            key = { applicant -> applicant.id }
        ) { applicant ->
            ApplicantItem(
                applicant = applicant,
                onClick = { onClick(applicant) },
            )
            Divider(
                color = KnowllyTheme.colors.grayEF,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(1.dp),
            )
        }
    }
}

@Composable
private fun ApplicantItem(
    applicant: Applicant,
    onClick: () -> Unit,
) {
    Row {
        Box(modifier = Modifier.size(40.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img_profile_placeholder),
                contentDescription = null,
            )
            AsyncImage(
                model = applicant.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = applicant.name, style = KnowllyTheme.typography.subtitle2)

            // TODO: 문구 기획 확정
            Text(
                text = "5월 23일 (수) 오후 5:00 (3시간 수업)",
                modifier = Modifier.padding(top = 2.dp),
                style = KnowllyTheme.typography.body2,
                color = KnowllyTheme.colors.gray44,
            )

            Text(
                text = applicant.content,
                modifier = Modifier.padding(top = 4.dp),
                style = KnowllyTheme.typography.body1,
                color = KnowllyTheme.colors.gray8F,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            KnowllyContainedButton(
                text = stringResource(id = R.string.applicant_form_shortcut),
                onClick = onClick,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(32.dp)
            )

        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun ApplicantContentPreview() {
    KnowllyTheme {
        ApplicantContent(
            uiState = ApplicantUiState.Empty,
            onNavigationClick = { },
            navigateToForm = { },
        )
    }
}
