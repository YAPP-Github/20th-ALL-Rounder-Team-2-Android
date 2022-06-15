package kr.co.knowledgerally.ui.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.ContainedBadge
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun MyPageScreen(viewModel: MyPageViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    MyPageScreen(
        state = state,
        onNotificationEnabledChange = { viewModel.updateNotificationEnabled(it) },
        navigateToProfile = { },
        navigateToTermsOfService = { },
        logout = { viewModel.logout() },
        withdrawal = { viewModel.withdrawal() },
    )
}

@Composable
private fun MyPageScreen(
    state: MyPageUiState,
    onNotificationEnabledChange: (Boolean) -> Unit,
    navigateToProfile: () -> Unit,
    navigateToTermsOfService: () -> Unit,
    logout: () -> Unit,
    withdrawal: () -> Unit,
) {
    when (state) {
        MyPageUiState.Loading -> MyPageScreen(
            notificationEnabled = false,
            onNotificationEnabledChange = { },
            versionName = "",
            userName = "",
            isCoach = false,
            navigateToProfile = { },
            navigateToTermsOfService = { },
            logout = { },
            withdrawal = { },
        )
        is MyPageUiState.Success -> MyPageScreen(
            notificationEnabled = state.notificationEnabled,
            onNotificationEnabledChange = onNotificationEnabledChange,
            versionName = state.versionName,
            userName = state.userName,
            isCoach = state.isCoach,
            navigateToProfile = navigateToProfile,
            navigateToTermsOfService = navigateToTermsOfService,
            logout = logout,
            withdrawal = withdrawal,
        )
    }
}

@Composable
private fun MyPageScreen(
    notificationEnabled: Boolean,
    onNotificationEnabledChange: (Boolean) -> Unit,
    versionName: String,
    userName: String,
    isCoach: Boolean,
    navigateToProfile: () -> Unit,
    navigateToTermsOfService: () -> Unit,
    logout: () -> Unit,
    withdrawal: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MyPageProfile(
            userName = userName,
            isCoach = isCoach,
            navigateToProfile = navigateToProfile,
        )
        MyPageDivider()
        MyPageItem(
            text = stringResource(id = R.string.mypage_allow_notification),
            onClick = { navigateToTermsOfService() },
            content = {
                MyPageSwitch(
                    checked = notificationEnabled,
                    onCheckedChange = onNotificationEnabledChange
                )
            }
        )
        MyPageItem(
            text = stringResource(id = R.string.mypage_app_version_info),
            onClick = { navigateToTermsOfService() },
            content = {
                Text(
                    text = versionName,
                    style = KnowllyTheme.typography.body1,
                )
            }
        )
        MyPageItem(
            text = stringResource(id = R.string.mypage_terms_of_service_and_policy),
            onClick = { navigateToTermsOfService() },
            content = { MyPageIcon() }
        )
        MyPageItem(
            text = stringResource(id = R.string.mypage_logout),
            onClick = { logout() },
            content = { MyPageIcon() }
        )
        MyPageItem(
            text = stringResource(id = R.string.mypage_withdrawal),
            onClick = { withdrawal() },
            content = { MyPageIcon() }
        )
    }
}

@Composable
private fun MyPageProfile(
    userName: String,
    isCoach: Boolean,
    navigateToProfile: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 12.dp,
                bottom = 40.dp,
                start = 24.dp
            )
    ) {
        Image(
            painter = painterResource(R.drawable.img_avatar),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = userName, style = KnowllyTheme.typography.subtitle1)
            Row(modifier = Modifier.padding(top = 4.dp)) {
                ContainedBadge(
                    text = stringResource(id = R.string.player),
                    contentColor = KnowllyTheme.colors.primaryDark,
                    backgroundColor = KnowllyTheme.colors.primary.copy(alpha = 0.1f)
                )
                if (isCoach) {
                    ContainedBadge(
                        text = stringResource(id = R.string.coach),
                        contentColor = KnowllyTheme.colors.secondaryLimeDark,
                        backgroundColor = KnowllyTheme.colors.secondary.copy(alpha = 0.2f),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
            KnowllyContainedButton(
                text = stringResource(id = R.string.mypage_show_more_profile),
                onClick = navigateToProfile,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(40.dp)
            )
        }
    }
}

@Composable
private fun MyPageItem(
    text: String,
    content: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable { onClick() }
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .then(clickableModifier)
            .padding(vertical = 20.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = KnowllyTheme.typography.subtitle4,
            modifier = Modifier.weight(1f)
        )
        content()
    }
    MyPageDivider()
}

@Composable
private fun MyPageIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_chevron_right),
        contentDescription = null,
        tint = KnowllyTheme.colors.gray00
    )
}

@Composable
private fun MyPageDivider() {
    Divider(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .height(1.dp),
        color = KnowllyTheme.colors.grayEF
    )
}

@Composable
private fun MyPageSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        modifier = Modifier.size(34.dp, 20.dp),
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = KnowllyTheme.colors.primaryDark,
            checkedTrackAlpha = 0.38f,
            uncheckedThumbColor = KnowllyTheme.colors.grayDD,
            uncheckedTrackColor = KnowllyTheme.colors.gray8F,
            uncheckedTrackAlpha = 0.38f
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MyPageScreenPreview() {
    KnowllyTheme {
        MyPageScreen(
            state = MyPageUiState.Loading,
            onNotificationEnabledChange = { },
            navigateToProfile = { },
            navigateToTermsOfService = { },
            logout = { },
            withdrawal = { },
        )
    }
}
