package kr.co.knowledgerally.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
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
fun LoginScreen(onLogin: () -> Unit) {
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
            LoginAcceptation()
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
            verticalArrangement = Arrangement.spacedBy(36.dp, Alignment.CenterVertically),
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_login_title),
                contentDescription = null
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
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFFEE500),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
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
fun LoginAcceptation() {
    val uriHandler = LocalUriHandler.current

    val policyString = stringResource(id = R.string.login_policy)
    val termsString = stringResource(id = R.string.login_terms)
    val acceptationString = stringResource(id = R.string.login_acceptation)

    val policyStringIndex = acceptationString.indexOf(policyString)
    val termsStringIndex = acceptationString.indexOf(termsString)

    val policyStringRange = policyStringIndex.rangeTo(policyStringIndex + policyString.length)
    val termsStringRange = termsStringIndex.rangeTo(termsStringIndex + termsString.length)

    ClickableText(
        text = buildAnnotatedString {
            append(acceptationString)
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
                uriHandler.openUri(LoginViewModel.PRIVACY_POLICY_URL)
            }
            in termsStringRange -> {
                uriHandler.openUri(LoginViewModel.TERMS_OF_SERVICE_URL)
            }
            else -> {}
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    KnowllyTheme {
        LoginScreen(onLogin = {})
    }
}
