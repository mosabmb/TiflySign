package com.example.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.GetSignsUseCase
import com.example.core.model.data.Sign
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    getSignsUseCase: GetSignsUseCase
) : ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val listSign = emptyList<Sign>()
    private val _signs = MutableStateFlow(listSign)

    init {
        viewModelScope.launch {
            getSignsUseCase.getSigns().collect{
                _signs.value = it
            }
        }
    }

    val signs = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_signs) { text, signs ->
            if (text.isBlank()) {
                emptyList()
            } else {
                delay(1000L)
                signs.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _signs.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }



}





