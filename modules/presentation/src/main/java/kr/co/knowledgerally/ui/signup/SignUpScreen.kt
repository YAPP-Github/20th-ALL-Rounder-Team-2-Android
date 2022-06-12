package kr.co.knowledgerally.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyCheckBoxText
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    navigateToTerms: () -> Unit,
    navigateToPolicy: () -> Unit
) {
    val signUpState = rememberSignUpState()
    SignUpContent(
        signUpState = signUpState,
        navigateToTerms = navigateToTerms,
        navigateToPolicy = navigateToPolicy,
    )
}

@Composable
fun SignUpContent(
    signUpState: SignUpState,
    navigateToTerms: () -> Unit,
    navigateToPolicy: () -> Unit
) {
    val termsState = signUpState.termsState
    val policyState = signUpState.policyState
    val notificationState = signUpState.notificationState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SignUpTopAppBar()
        Column(
            modifier = Modifier
                .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp)
        ) {
            SignUpTitle()
            VerticalSpacer(20.dp)
            AllAcception(
                areAllAccepted = signUpState.isAllChecked.value,
                onAllClick = { signUpState.toggleAll() }
            )
            Divider(
                color = KnowllyTheme.colors.grayDD,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            TermsAcception(
                isTermsAccepted = termsState.isChecked,
                onTermsClick = { termsState.toggle() },
                onShowTerms = navigateToTerms
            )
            PolicyAcception(
                isPolicyAccepted = policyState.isChecked,
                onPolicyClick = { policyState.toggle() },
                onShowPolicy = navigateToPolicy
            )
            NotificationAcception(
                isNotificationAccepted = notificationState.isChecked,
                onNotificationClick = { notificationState.toggle() }
            )
            Spacer(modifier = Modifier.weight(1f))
            SignUpButton(
                isTermsAccepted = signUpState.isRequired,
                isPolicyAccepted = signUpState.isRequired
            )
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
fun SignUpTitle() {
    Text(
        text = stringResource(R.string.signup_acceptation),
        style = KnowllyTheme.typography.headline3,
    )
}

@Composable
fun AllAcception(
    areAllAccepted: Boolean,
    onAllClick: (Boolean) -> Unit,
) {
    KnowllyCheckBoxText(
        checked = areAllAccepted,
        onCheckedChanged = onAllClick,
        text = stringResource(R.string.signup_accept_all)
    )
}

@Composable
fun TermsAcception(
    isTermsAccepted: Boolean,
    onTermsClick: (Boolean) -> Unit,
    onShowTerms: () -> Unit
) {
    KnowllyCheckBoxText(
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
        onActionTap = onShowTerms
    )
}

@Composable
fun PolicyAcception(
    isPolicyAccepted: Boolean,
    onPolicyClick: (Boolean) -> Unit,
    onShowPolicy: () -> Unit
) {
    KnowllyCheckBoxText(
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
        onActionTap = onShowPolicy
    )
}

@Composable
fun NotificationAcception(
    isNotificationAccepted: Boolean,
    onNotificationClick: (Boolean) -> Unit
) {
    Column {
        KnowllyCheckBoxText(
            checked = isNotificationAccepted,
            onCheckedChanged = onNotificationClick,
            text = stringResource(R.string.signup_accept_notice)
        )
        Text(
            text = stringResource(R.string.signup_help_notice),
            style = KnowllyTheme.typography.caption,
            color = KnowllyTheme.colors.gray6B,
            modifier = Modifier.padding(start = 36.dp)
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
private fun SignUpScreenPreview() {
    KnowllyTheme {
        SignUpContent(
            signUpState = rememberSignUpState(),
            navigateToTerms = { },
            navigateToPolicy = { }
        )
    }
}
