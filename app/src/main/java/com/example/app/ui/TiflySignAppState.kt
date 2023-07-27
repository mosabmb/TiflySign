package com.example.app.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.example.app.navigation.TopLevelDestination
import com.example.core.data.util.NetworkMonitor
import com.example.core.ui.TrackDisposableJank
import com.example.feature.categories.navigation.categoriesGraphRoutePattern
import com.example.feature.categories.navigation.navigateToCategories
import com.example.feature.home.navigation.homeGraphRoutePattern
import com.example.feature.home.navigation.navigateToHomeGraph
import com.example.feature.search.navigation.navigateToSignsSearch
import com.example.feature.search.navigation.searchGraphRoutePattern
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@Composable
fun rememberTiflyAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): TiflySignAppState {


    NavigationTrackingSideEffect(navController)

    return remember(
        navController,
        coroutineScope,
        networkMonitor,
        navController,
    ) {
        TiflySignAppState(
            navController,
            coroutineScope,
            windowSizeClass,
            networkMonitor,

        )
    }
}

@Stable
class TiflySignAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,

) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeGraphRoutePattern -> TopLevelDestination.HOME
            categoriesGraphRoutePattern -> TopLevelDestination.CATEGORY
            searchGraphRoutePattern -> TopLevelDestination.SEARCH
            else -> null
        }


    val shouldShowBottomBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            "sign_detail/{signId}" -> false
            else -> { true }
        }

    val shouldShowTopBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            "sign_detail/{signId}" -> false
            else -> { true }
        }


    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHomeGraph(topLevelNavOptions)
                TopLevelDestination.CATEGORY -> navController.navigateToCategories(topLevelNavOptions)
                TopLevelDestination.SEARCH -> navController.navigateToSignsSearch(topLevelNavOptions)

            }
        }
    }

}

@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
