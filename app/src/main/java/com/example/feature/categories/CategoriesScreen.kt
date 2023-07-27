package com.example.feature.categories


import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.app.ui.theme.TiflySignAppTheme
import com.example.core.model.data.Category
import com.example.core.ui.CategoryPreviewParameterProvider
import com.example.core.ui.DevicePreviews
import com.example.core.ui.TrackScrollJank
import com.example.feature.categories.components.CategoriesGrid


@Composable
fun CategoriesScreen(
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel()
) {

    val categoryUiState by viewModel.uiState.collectAsStateWithLifecycle()

    CategoriesScreen(
        categoriesUiState = categoryUiState,
        onCategoryClick = onCategoryClick,
        modifier = modifier
    )

}

@Composable
internal fun CategoriesScreen(
    categoriesUiState: CategoriesUiState,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val state = rememberLazyListState()

    TrackScrollJank(scrollableState = state, stateName = "signs:screen")

    when (categoriesUiState) {
        is CategoriesUiState.Categories -> {
            CategoriesGrid(
                categories = categoriesUiState.categories,
                onClick = onCategoryClick
            )
        }
        CategoriesUiState.Loading -> {}
        CategoriesUiState.Empty -> {}

    }

}


@DevicePreviews
@Composable
fun CategoriesScreenPopulated(@PreviewParameter(CategoryPreviewParameterProvider::class) categories: List<Category>) {
    TiflySignAppTheme {
        CategoriesScreen(
            categoriesUiState =
            CategoriesUiState.Categories(categories = categories),
            onCategoryClick = {},
        )
    }
}

