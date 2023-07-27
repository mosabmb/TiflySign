package com.example.feature.categories.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.feature.categories.CategoriesScreen


const val categoriesGraphRoutePattern = "categories_graph"
const val categoriesRoute = "categories_route"

fun NavController.navigateToCategories(navOptions: NavOptions? = null) {
    this.navigate(categoriesGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.categoriesScreen(
    onCategoryClick: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = categoriesGraphRoutePattern,
        startDestination = categoriesRoute,
    ) {

        composable(route = categoriesRoute) {
            CategoriesScreen(onCategoryClick)
        }

        nestedGraphs()

    }
}