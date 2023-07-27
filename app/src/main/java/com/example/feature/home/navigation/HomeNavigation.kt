package com.example.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.feature.home.HomeRoute


const val homeGraphRoutePattern = "home_graph"
const val homeRoute = "home_route"

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    this.navigate(homeGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.homeGraph(
    onSignClick: (String) -> Unit,
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeRoute,
    ) {

        composable(route = homeRoute) {
            HomeRoute(
                onSignClick = onSignClick
            )
        }
    }
}