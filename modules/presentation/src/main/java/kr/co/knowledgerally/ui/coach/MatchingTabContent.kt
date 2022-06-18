package kr.co.knowledgerally.ui.coach

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.domain.model.Applicant
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.Banner
import kr.co.knowledgerally.ui.component.RoundRect
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun MatchingTabContent(
    matchingList: List<ClassUiState.Matching>,
    navigateToApplicant: (classId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        CoachTitle(text = stringResource(R.string.coach_matching_title))
        Banner(
            text = stringResource(id = R.string.coach_matching_banner),
            modifier = Modifier.padding(top = 10.dp),
        )
        CoachDivider(Modifier.padding(top = 24.dp))

        matchingList.forEach { matching ->
            MatchingItem(matching = matching, navigateToApplicant = navigateToApplicant)
        }
    }
}

@Composable
private fun MatchingItem(
    matching: ClassUiState.Matching,
    navigateToApplicant: (classId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        VerticalSpacer(height = 16.dp)
        MatchingItemHeader(text = matching.className)
        MatchingItemApplicant(
            applicants = matching.applicants,
            onClick = { navigateToApplicant(matching.classId) },
            modifier = Modifier.padding(top = 8.dp)
        )
        VerticalSpacer(height = 16.dp)
        CoachDivider()
    }
}

@Composable
private fun MatchingItemHeader(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        RoundRect(radius = 8.dp, width = 4.dp)
        Text(
            text = text,
            style = KnowllyTheme.typography.subtitle2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, top = 2.dp, bottom = 2.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun MatchingItemApplicant(
    applicants: List<Applicant>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(start = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(
                id = R.string.coach_matching_applicant_fmt,
                applicants.size
            ),
            style = KnowllyTheme.typography.body2,
            color = KnowllyTheme.colors.primaryDark,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = KnowllyTheme.colors.primaryDark,
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}
