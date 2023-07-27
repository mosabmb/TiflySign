package com.example.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.feature.search.SearchRoute


const val searchGraphRoutePattern = "search_graph"
const val searchRoute = "search_route"

fun NavController.navigateToSignsSearch(navOptions: NavOptions? = null) {
    this.navigate(searchGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.searchDetailsScreen(
    onSignClick: (String) -> Unit,
) {

    navigation(
        route = searchGraphRoutePattern,
        startDestination = searchRoute,
    ) {

        composable(route = searchRoute) {
            SearchRoute(
                onSignClick = { signId ->
                    onSignClick(signId)
                }
            )
        }

    }


}