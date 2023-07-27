package com.example.feature.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.GetCategoriesUseCase
import com.example.core.model.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class CategoriesViewModel @Inject constructor(
    getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {


    val uiState: StateFlow<CategoriesUiState> =
        getCategoriesUseCase.getCategories().map(
            CategoriesUiState::Categories,
        ).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CategoriesUiState.Loading,
        )


}


sealed interface CategoriesUiState {
    object Loading : CategoriesUiState
    data class Categories(val categories: List<Category>, ) : CategoriesUiState
    object Empty : CategoriesUiState
}