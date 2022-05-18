package kr.co.knowledgerally.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kr.co.knowledgerally.ui.main.navigation.MainDestination
import kr.co.knowledgerally.ui.main.navigation.MainNavHost
import kr.co.knowledgerally.ui.main.navigation.TOP_LEVEL_DESTINATIONS
import kr.co.knowledgerally.ui.main.navigation.rememberMainNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navigation = rememberMainNavigation(navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            MainNavigationBar(
                currentDestination = currentDestination,
                onNavigate = { navigation.navigateTo(it) }
            )
        },
    ) { padding ->
        MainNavHost(
            navController = navController,
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
private fun MainNavigationBar(
    currentDestination: NavDestination?,
    onNavigate: (MainDestination) -> Unit,
) {
    NavigationBar(tonalElevation = 0.dp) {
        TOP_LEVEL_DESTINATIONS.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true

            NavigationBarItem(
                selected = selected,
                destination = destination,
                onClick = { onNavigate(destination) }
            )
        }
    }
}

@Composable
private fun RowScope.NavigationBarItem(
    selected: Boolean,
    destination: MainDestination,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick,
            )
            .weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageVector = if (selected) {
                destination.selectedIcon
            } else {
                destination.unselectedIcon
            }
            Icon(imageVector = imageVector, contentDescription = null)
            Spacer(modifier = modifier.height(4.dp))
            Text(text = stringResource(id = destination.iconTextResId))
        }
    }
}
