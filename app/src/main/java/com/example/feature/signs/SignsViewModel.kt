package com.example.feature.signs


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.result.Result
import com.example.core.common.result.asResult
import com.example.core.domain.GetSignsUseCase
import com.example.core.model.data.Sign
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class SignsViewModel @Inject constructor(
    getSignsUseCase: GetSignsUseCase,
    @CategoryId categoryId: String
): ViewModel() {



    var category =  categoryId

    var uiState: StateFlow<SignsUiState> = signsUiState(
        categoryName = category,
        getSignsUseCase = getSignsUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SignsUiState.Loading,
    )

    var uiState1: StateFlow<SignsUiState> = signsUiState(
        categoryName = "Famille",
        getSignsUseCase = getSignsUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SignsUiState.Loading,
    )



}

private fun signsUiState(
    categoryName: String,
    getSignsUseCase: GetSignsUseCase,
): Flow<SignsUiState> {

    val signsStream: Flow<List<Sign>> = getSignsUseCase.getSignByCategory(categoryName)

    return signsStream
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    SignsUiState.Signs( result.data )
                }
                is Result.Loading -> {
                    SignsUiState.Loading
                }
                is Result.Error -> {
                    SignsUiState.Empty
                }
            }
        }
}


sealed interface SignsUiState {
    object Loading : SignsUiState
    data class Signs(val signs: List<Sign>) : SignsUiState
    object Empty : SignsUiState
}