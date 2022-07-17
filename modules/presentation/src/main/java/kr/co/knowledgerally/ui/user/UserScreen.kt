package kr.co.knowledgerally.ui.user

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.co.knowledgerally.bridge.BridgeDelegate
import kr.co.knowledgerally.bridge.WebViewState
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.KnowllyWebView
import kr.co.knowledgerally.ui.profile.ProfileActivity
import kr.co.knowledgerally.ui.profile.state.Mode
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun UserScreen(
    state: WebViewState,
    delegate: BridgeDelegate,
    refresh: () -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode == Activity.RESULT_OK) {
                refresh()
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        KnowllyTopAppBar(
            onNavigationClick = navigateUp,
            actions = {
                TextButton(
                    onClick = {
                        val intent = ProfileActivity.getIntent(context, Mode.Edit)
                        launcher.launch(intent)
                    },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.user_edit),
                        color = KnowllyTheme.colors.primaryDark,
                        style = KnowllyTheme.typography.subtitle2
                    )
                }
            }
        )

        KnowllyWebView(
            state = state,
            delegate = delegate,
        )
    }
}
