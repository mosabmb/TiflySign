package com.example.feature.signs.navigation

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.core.common.decoder.StringDecoder
import com.example.feature.details.SignDetailsRoute
import com.example.feature.signs.SignsRoute
import com.example.feature.signs.SignsViewModel


@VisibleForTesting
internal const val signIdArg = "signId"

internal class SignDetailArgs(val signId: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(stringDecoder.decodeString(checkNotNull(savedStateHandle[signIdArg])))
}
fun NavController.navigateToSignDetailGraph(signId: String) {
    val encodedId = Uri.encode(signId)
    this.navigate("sign_detail/${encodedId}")
}


@VisibleForTesting
internal const val categoryIdArg = "categoryId"

internal class SignsArgs(val categoryName: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
            this(checkNotNull(savedStateHandle[categoryIdArg]))
}
fun NavController.navigateToSignsGraph(categoryName: String) {
    val encodedId = Uri.encode(categoryName)
    this.navigate("signs_graph/${encodedId}")
}


fun NavGraphBuilder.signsScreen(
    onSignClick: (String) -> Unit,
    onBackClick: () -> Unit,
) {

    navigation(
        route = "signs_graph/{categoryId}",
        startDestination = "signs_route/{categoryId}",
        arguments = listOf(navArgument("categoryId") { type = NavType.StringType }),
    ) {

        composable( route = "signs_route/{categoryId}" )
        { backStackEntry->
            val categoryName = backStackEntry.arguments?.getString("categoryId")
            if (categoryName != null) {

                SignsRoute(
                    viewModel = hiltViewModel<SignsViewModel>(),
                    category = categoryName,
                    onSignClick = { signId ->
                        onSignClick(signId)
                    }
                )
            }
        }

        composable( route = "sign_detail/{signId}",)
        { backStackEntry->
            val signId = backStackEntry.arguments?.getString("signId").toString()
            SignDetailsRoute(
                signId,
                onBackClick,
            )
        }


    }



}
