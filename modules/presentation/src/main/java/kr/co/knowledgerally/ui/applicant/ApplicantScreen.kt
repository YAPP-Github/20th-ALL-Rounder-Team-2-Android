package kr.co.knowledgerally.ui.applicant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun ApplicantScreen(
    viewModel: ApplicantViewModel,
    navigateUp: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ApplicantContent(onNavigationClick = navigateUp)
    }
}

@Composable
fun ApplicantContent(onNavigationClick: () -> Unit) {
    Column {
        KnowllyTopAppBar(onNavigationClick = onNavigationClick)
        Text(
            text = stringResource(id = R.string.applicant_title),
            style = KnowllyTheme.typography.headline3,
            modifier = Modifier.padding(start = 24.dp, top = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.applicant_description),
            modifier = Modifier
                .padding(start = 24.dp, top = 4.dp, end = 24.dp)
                .fillMaxWidth(),
            style = KnowllyTheme.typography.body1,
            color = KnowllyTheme.colors.gray8F,
        )
        LazyColumn(
            contentPadding = PaddingValues(start = 24.dp, top = 36.dp, end = 24.dp, bottom = 36.dp)
        ) {

        }
    }
}

@Composable
fun ApplicantItem() {
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
private fun ApplicantContentPreview() {
    KnowllyTheme {
        ApplicantContent(
            onNavigationClick = { },
        )
    }
}
