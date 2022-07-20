package kr.co.knowledgerally.ui.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyCheckBoxText
import kr.co.knowledgerally.ui.component.KnowllyContainedButton
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun SignUpScreen(
    navigateUp: () -> Unit,
    navigateToTerms: () -> Unit,
    navigateToPolicy: () -> Unit,
    signUp: () -> Unit
) {
    val signUpState = rememberSignUpState()
    SignUpScreen(
        signUpState = signUpState,
        navigateUp = navigateUp,
        navigateToTerms = navigateToTerms,
        navigateToPolicy = navigateToPolicy,
        signUp = signUp
    )
}

@Composable
private fun SignUpScreen(
    signUpState: SignUpState,
    navigateUp: () -> Unit,
    navigateToTerms: () -> Unit,
    navigateToPolicy: () -> Unit,
    signUp: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyTopAppBar(navigationType = NavigationType.None)
        SignUpContent(
            signUpState = signUpState,
            navigateToTerms = navigateToTerms,
            navigateToPolicy = navigateToPolicy,
            signUp = signUp
        )
    }
}

@Composable
private fun SignUpContent(
    signUpState: SignUpState,
    navigateToTerms: () -> Unit,
    navigateToPolicy: () -> Unit,
    signUp: () -> Unit
) {
    val termsState = signUpState.termsState
    val policyState = signUpState.policyState

    Column(
        modifier = Modifier
            .padding(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 24.dp)
    ) {
        SignUpTitle()
        VerticalSpacer(20.dp)
        CheckItem(
            text = stringResource(id = R.string.signup_accept_all),
            checked = signUpState.isAllChecked.value,
            onCheckedChange = { signUpState.toggleAll() },
        )
        Divider(
            color = KnowllyTheme.colors.grayDD,
            modifier = Modifier.padding(vertical = 12.dp)
        )
        CheckItem(
            text = stringResource(id = R.string.signup_accept_terms),
            checkState = termsState,
            onClick = navigateToTerms
        )
        CheckItem(
            text = stringResource(id = R.string.signup_accept_policy),
            checkState = policyState,
            onClick = navigateToPolicy
        )
        Spacer(modifier = Modifier.weight(1f))
        SignUpButton(enabled = signUpState.isRequired, onClick = { signUp() })
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
fun CheckItem(
    text: String,
    checkState: CheckState,
    onClick: (() -> Unit)? = null,
) {
    CheckItem(
        text = text,
        checked = checkState.isChecked,
        onCheckedChange = { checkState.toggle() },
        onClick = onClick,
    )
}

@Composable
fun CheckItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: (() -> Unit)? = null,
) {
    val actionIcon: (@Composable () -> Unit) = {
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = KnowllyTheme.colors.gray00,
            modifier = Modifier.size(20.dp)
        )
    }
    KnowllyCheckBoxText(
        checked = checked,
        onCheckedChanged = onCheckedChange,
        text = text,
        modifier = Modifier.padding(vertical = 12.dp),
        actionIcon = if (onClick == null) null else actionIcon,
        onActionTap = onClick
    )
}

@Composable
fun SignUpButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    KnowllyContainedButton(
        text = stringResource(R.string.signup_signup),
        enabled = enabled,
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    KnowllyTheme {
        SignUpScreen(
            signUpState = rememberSignUpState(),
            navigateUp = { },
            navigateToTerms = { },
            navigateToPolicy = { },
            signUp = { }
        )
    }
}
