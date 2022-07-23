package kr.co.knowledgerally.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kr.co.knowledgerally.ui.R
import kr.co.knowledgerally.ui.coach.CoachScreen
import kr.co.knowledgerally.ui.component.KnowllyTopAppBar
import kr.co.knowledgerally.ui.component.Logo
import kr.co.knowledgerally.ui.component.NavigationType
import kr.co.knowledgerally.ui.home.HomeScreen
import kr.co.knowledgerally.ui.main.dialog.WelcomeDialog
import kr.co.knowledgerally.ui.mypage.MyPageScreen
import kr.co.knowledgerally.ui.player.PlayerScreen
import kr.co.knowledgerally.ui.theme.KnowllyTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToRegister: () -> Unit,
    navigateToBall: () -> Unit,
    navigateToNotification: () -> Unit,
) {
    val navController = rememberAnimatedNavController()
    val navigation = rememberMainNavigation(navController, navigateToRegister)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showWelcome by viewModel.showWelcome.collectAsState()
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            KnowllyTopAppBar(
                navigationType = NavigationType.None,
                actions = {
                    val visible = currentDestination?.route == MainDestination.Home.route
                    Logo(Modifier.padding(start = 24.dp), visible = visible)
                    Spacer(modifier = Modifier.weight(1f))
                    BallIcon(ballCount = user?.ballCount ?: 0, onClick = navigateToBall)
                    NotificationIcon(onClick = navigateToNotification)
                }
            )
        },
        modifier = Modifier,
        bottomBar = {
            MainNavigationBar(
                currentDestination = currentDestination,
                onNavigate = { navigation.navigateTo(it) },
            )
        },
    ) { padding ->
        AnimatedNavHost(
            navController = navigation.navController,
            startDestination = MainDestination.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(route = MainDestination.Home.route) {
                HomeScreen()
            }
            composable(route = MainDestination.Player.route) {
                PlayerScreen()
            }
            composable(MainDestination.Coach.route) {
                CoachScreen(
                    navigateToRegister = { navigation.navigateTo(MainDestination.Register) }
                )
            }
            composable(MainDestination.MyPage.route) {
                MyPageScreen()
            }
        }
    }

    if (showWelcome) {
        WelcomeDialog(onDismiss = { viewModel.shownWelcome() })
    }
}

@Composable
private fun BallIcon(
    ballCount: Int,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(36.dp),
        color = Color.Unspecified,
        border = BorderStroke(width = 2.dp, color = KnowllyTheme.colors.grayEF),
        modifier = Modifier
            .clip(RoundedCornerShape(36.dp))
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ball),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = ballCount.toString(), style = KnowllyTheme.typography.subtitle4
            )
        }
    }
}

@Composable
private fun NotificationIcon(onClick: () -> Unit) {
    Icon(
        painter = painterResource(id = R.drawable.ic_alarm),
        contentDescription = null,
        tint = KnowllyTheme.colors.gray00,
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(4.dp)
    )
}

@Composable
private fun MainNavigationBar(
    currentDestination: NavDestination?,
    onNavigate: (MainDestination) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Divider(
            thickness = 1.dp,
            color = KnowllyTheme.colors.grayEF
        )
        Row(
            modifier = Modifier
                .height(NavigationBarHeight)
                .background(KnowllyTheme.colors.grayFF)
        ) {

            MainDestination.values().forEach { destination ->
                val selected = destination in currentDestination

                NavigationBarItem(
                    selected = selected,
                    destination = destination,
                    onClick = { onNavigate(destination) }
                )
            }
        }
    }
}

private operator fun NavDestination?.contains(destination: MainDestination): Boolean {
    if (this == null) {
        return false
    }
    return hierarchy.any { it.route == destination.route }
}

@Composable
private fun RowScope.NavigationBarItem(
    selected: Boolean,
    destination: MainDestination,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .weight(1f)
            .selectable(
                selected = selected,
                onClick = onClick,
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = destination.icon(selected)),
            contentDescription = null,
            tint = destination.color(selected)
        )
        Text(
            text = destination.text(),
            style = KnowllyTheme.typography.caption,
            color = destination.color(selected)
        )
    }
}

private val NavigationBarHeight: Dp = 56.dp

@Preview(showBackground = true)
@Composable
fun MainNavigationBarPreview() {
    KnowllyTheme {
        MainNavigationBar(
            currentDestination = null,
            onNavigate = { }
        )
    }
}
