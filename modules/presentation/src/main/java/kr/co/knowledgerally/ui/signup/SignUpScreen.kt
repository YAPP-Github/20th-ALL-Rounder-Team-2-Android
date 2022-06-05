package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.KnowllyCheckBox
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.signup.SignUpViewModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SignUpScreen(viewModel: SignUpViewModel) {
    SignUpScreen(
        areAllAccepted = viewModel.areAllAccepted.value,
        isTermsAccepted = viewModel.isTermsAccepted.value,
        isPolicyAccepted = viewModel.isPolicyAccepted.value,
        isNotificationAccepted = viewModel.isNotificationAccepted.value,
        onAllClick = viewModel::setAll,
        onTermsClick = viewModel::setTerms,
        onPolicyClick = viewModel::setPolicy,
        onNotificationClick = viewModel::setNotification
    )
}

@Composable
fun SignUpScreen(
    areAllAccepted: Boolean,
    isTermsAccepted: Boolean,
    isPolicyAccepted: Boolean,
    isNotificationAccepted: Boolean,
    onAllClick: (Boolean) -> Unit,
    onTermsClick: (Boolean) -> Unit,
    onPolicyClick: (Boolean) -> Unit,
    onNotificationClick: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SignUpTopAppBar()
        Column(
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 24.dp)
        ) {
            SignUpTitle(modifier = Modifier.padding(horizontal = 12.dp))
            VerticalSpacer(24.dp)
            AllAcception(areAllAccepted = areAllAccepted, onAllClick = onAllClick)
            Divider(modifier = Modifier.padding(horizontal = 12.dp))
            TermsAcception(isTermsAccepted = isTermsAccepted, onTermsClick = onTermsClick)
            PolicyAcception(isPolicyAccepted = isPolicyAccepted, onPolicyClick = onPolicyClick)
            NotificationAcception(
                isNotificationAccepted = isNotificationAccepted,
                onNotificationClick = onNotificationClick
            )
            Spacer(modifier = Modifier.weight(1f))
            SignUpButton(isTermsAccepted = isTermsAccepted, isPolicyAccepted = isPolicyAccepted)
        }
    }
}

@Composable
fun SignUpTopAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SignUpTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.signup_acception),
        style = KnowllyTheme.typography.headline4,
        modifier = modifier
    )
}

@Composable
fun AllAcception(
    areAllAccepted: Boolean,
    onAllClick: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 0.dp)
    ) {
        KnowllyCheckBox(checked = areAllAccepted, onCheckedChanged = onAllClick)
        Text(
            text = stringResource(R.string.signup_accept_all),
            style = KnowllyTheme.typography.subtitle4
        )
    }
}

@Composable
fun TermsAcception(
    isTermsAccepted: Boolean,
    onTermsClick: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        KnowllyCheckBox(checked = isTermsAccepted, onCheckedChanged = onTermsClick)
        Text(
            text = stringResource(R.string.signup_accept_terms),
            style = KnowllyTheme.typography.subtitle4
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
        HorizontalSpacer(width = 8.dp)
    }
}

@Composable
fun PolicyAcception(
    isPolicyAccepted: Boolean,
    onPolicyClick: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        KnowllyCheckBox(checked = isPolicyAccepted, onCheckedChanged = onPolicyClick)
        Text(
            text = stringResource(R.string.signup_accept_policy),
            style = KnowllyTheme.typography.subtitle4
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
        HorizontalSpacer(width = 8.dp)
    }
}

@Composable
fun NotificationAcception(
    isNotificationAccepted: Boolean,
    onNotificationClick: (Boolean) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            KnowllyCheckBox(
                checked = isNotificationAccepted,
                onCheckedChanged = onNotificationClick
            )
            Text(
                text = stringResource(R.string.signup_accept_notice),
                style = KnowllyTheme.typography.subtitle4
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
            }
            HorizontalSpacer(width = 8.dp)
        }
        Text(
            text = stringResource(R.string.signup_help_notice),
            style = KnowllyTheme.typography.caption,
            color = KnowllyTheme.colors.gray6B,
            modifier = Modifier.padding(start = 48.dp)
        )
    }
}

@Composable
fun SignUpButton(
    isTermsAccepted: Boolean,
    isPolicyAccepted: Boolean
) {
    KnowllyContainedButton(
        text = stringResource(R.string.signup_signup),
        enabled = isTermsAccepted and isPolicyAccepted,
        onClick = { },
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun SignUpScreemPreview() {
    KnowllyTheme {
        SignUpScreen(
            areAllAccepted = true,
            isTermsAccepted = true,
            isPolicyAccepted = true,
            isNotificationAccepted = false,
            onAllClick = {},
            onTermsClick = {},
            onPolicyClick = {},
            onNotificationClick = {}
        )
    }
}