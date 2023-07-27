package com.example.feature.details


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.decoder.StringDecoder
import com.example.core.common.result.Result
import com.example.core.common.result.asResult
import com.example.core.domain.GetSignsUseCase
import com.example.core.model.data.Sign
import com.example.feature.signs.navigation.SignDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class SignDetailsViewModel @Inject constructor(
    getSignsUseCase: GetSignsUseCase,
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
) : ViewModel() {


    private val SignsDetailArgs: SignDetailArgs = SignDetailArgs(savedStateHandle, stringDecoder)

    val signId = SignsDetailArgs.signId

    val uiState: StateFlow<SignDetailUiState> = signDetailUiState(
        signId = signId,
        getSignsUseCase = getSignsUseCase
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SignDetailUiState.Loading,
    )
}

private fun signDetailUiState(
    signId: String,
    getSignsUseCase: GetSignsUseCase,
): Flow<SignDetailUiState> {

    val signsStream: Flow<List<Sign>> = getSignsUseCase.getSignById(signId)

    return signsStream
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> {
                    SignDetailUiState.Success( result.data )
                }
                is Result.Loading -> {
                    SignDetailUiState.Loading
                }
                is Result.Error -> {
                    SignDetailUiState.Error
                }
            }
        }
}

sealed interface SignDetailUiState {
    object Loading : SignDetailUiState
    data class Success(val signs: List<Sign>) : SignDetailUiState
    object Error : SignDetailUiState

}



