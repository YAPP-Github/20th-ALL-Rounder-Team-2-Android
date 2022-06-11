package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.HorizontalSpacer
import kr.co.knowledgerally.ui.component.VerticalSpacer
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun LoginScreen(onLogin: () -> Unit, navigateToTerms: () -> Unit, navigateToPolicy: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginGraphic(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = KnowllyTheme.colors.grayFF)
                .fillMaxWidth()
                .height(192.dp)
                .padding(start = 24.dp, top = 60.dp, end = 24.dp, bottom = 48.dp)
        ) {
            KakaoLoginButton(onClick = onLogin)
            VerticalSpacer(height = 24.dp)
            LoginAgreement(navigateToTerms = navigateToTerms, navigateToPolicy = navigateToPolicy)
        }
    }
}

@Composable
fun LoginGraphic(modifier: Modifier = Modifier) {
    Surface(
        color = KnowllyTheme.colors.primaryLight,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_login_title),
                contentDescription = null,
                modifier = Modifier.padding(vertical = 36.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_login_illustration),
                contentDescription = null
            )
        }
    }
}

@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEE500)
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_kakao),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            HorizontalSpacer(width = 12.dp)
            Text(
                text = stringResource(R.string.login_kakao),
                color = Color.Black.copy(alpha = 0.85f),
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun LoginAgreement(navigateToTerms: () -> Unit, navigateToPolicy: () -> Unit) {
    val policyString = stringResource(id = R.string.login_policy)
    val termsString = stringResource(id = R.string.login_terms)
    val acceptionString = stringResource(id = R.string.login_acception)

    val policyStringRange = IntRange(
        acceptionString.indexOf(policyString),
        acceptionString.indexOf(policyString) + policyString.length
    )
    val termsStringRange = IntRange(
        acceptionString.indexOf(termsString),
        acceptionString.indexOf(termsString) + termsString.length
    )

    ClickableText(
        text = buildAnnotatedString {
            append(acceptionString)
            listOf(policyStringRange, termsStringRange).forEach {
                addStyle(
                    style = SpanStyle(textDecoration = TextDecoration.Underline),
                    start = it.first,
                    end = it.last
                )
            }
        },
        style = KnowllyTheme.typography.caption.copy(color = KnowllyTheme.colors.gray8F)
    ) { position ->
        when (position) {
            in policyStringRange -> {
                navigateToPolicy()
            }
            in termsStringRange -> {
                navigateToTerms()
            }
            else -> {}
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    KnowllyTheme {
        LoginScreen(onLogin = {}, navigateToTerms = {}, navigateToPolicy = {})
    }
}
