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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyCheckBoxTile
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.signup.SignUpViewModel
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SignUpScreen(viewModel: SignUpViewModel, onShowTerms: () -> Unit, onShowPolicy: () -> Unit) {
    SignUpScreen(
        areAllAccepted = viewModel.areAllAccepted.value,
        isTermsAccepted = viewModel.isTermsAccepted.value,
        isPolicyAccepted = viewModel.isPolicyAccepted.value,
        isNotificationAccepted = viewModel.isNotificationAccepted.value,
        onAllClick = viewModel::setAll,
        onTermsClick = viewModel::setTerms,
        onPolicyClick = viewModel::setPolicy,
        onNotificationClick = viewModel::setNotification,
        onShowTerms = onShowTerms,
        onShowPolicy = onShowPolicy
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
    onShowTerms: () -> Unit,
    onShowPolicy: () -> Unit
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
            TermsAcception(
                isTermsAccepted = isTermsAccepted,
                onTermsClick = onTermsClick,
                onShowTerms = onShowTerms
            )
            PolicyAcception(
                isPolicyAccepted = isPolicyAccepted,
                onPolicyClick = onPolicyClick,
                onShowPolicy = onShowPolicy
            )
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
        style = KnowllyTheme.typography.headline3,
        modifier = modifier
    )
}

@Composable
fun AllAcception(
    areAllAccepted: Boolean,
    onAllClick: (Boolean) -> Unit,
) {
    KnowllyCheckBoxTile(
        checked = areAllAccepted,
        onCheckedChanged = onAllClick,
        text = stringResource(R.string.signup_accept_all),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}

@Composable
fun TermsAcception(
    isTermsAccepted: Boolean,
    onTermsClick: (Boolean) -> Unit,
    onShowTerms: () -> Unit
) {
    KnowllyCheckBoxTile(
        checked = isTermsAccepted,
        onCheckedChanged = onTermsClick,
        text = stringResource(R.string.signup_accept_terms),
        actionIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        },
        onActionTap = onShowTerms,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    )
}

@Composable
fun PolicyAcception(
    isPolicyAccepted: Boolean,
    onPolicyClick: (Boolean) -> Unit,
    onShowPolicy: () -> Unit
) {
    KnowllyCheckBoxTile(
        checked = isPolicyAccepted,
        onCheckedChanged = onPolicyClick,
        text = stringResource(R.string.signup_accept_policy),
        actionIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        },
        onActionTap = onShowPolicy,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NotificationAcception(
    isNotificationAccepted: Boolean,
    onNotificationClick: (Boolean) -> Unit
) {
    Column {
        KnowllyCheckBoxTile(
            checked = isNotificationAccepted,
            onCheckedChanged = onNotificationClick,
            text = stringResource(R.string.signup_accept_notice),
            modifier = Modifier.fillMaxWidth()
        )
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
            onNotificationClick = {},
            onShowTerms = {},
            onShowPolicy = {}
        )
    }
}
