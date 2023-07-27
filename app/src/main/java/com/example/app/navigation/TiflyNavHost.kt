package com.example.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.app.ui.TiflySignAppState
import com.example.feature.categories.navigation.categoriesScreen
import com.example.feature.home.navigation.homeGraph
import com.example.feature.home.navigation.homeGraphRoutePattern
import com.example.feature.search.navigation.searchDetailsScreen
import com.example.feature.signs.navigation.navigateToSignDetailGraph
import com.example.feature.signs.navigation.navigateToSignsGraph
import com.example.feature.signs.navigation.signsScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TiflyNavHost(
    appState: TiflySignAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeGraphRoutePattern,
) {

    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = "home_graph",
        modifier = modifier,
    ) {


        homeGraph(
            onSignClick = { signId ->
                navController.navigateToSignDetailGraph(signId)

            }
        )

        searchDetailsScreen(
            onSignClick = { signId ->
                navController.navigateToSignDetailGraph(signId)
            }
        )

        categoriesScreen (
            onCategoryClick = { categoryName ->
                navController.navigateToSignsGraph(categoryName)
            },
            nestedGraphs = {
                signsScreen(
                    onSignClick = { signId ->
                        navController.navigateToSignDetailGraph(signId)
                    },
                    onBackClick = navController::popBackStack
                )
            }
        )

    }

}