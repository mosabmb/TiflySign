package com.example.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.designsystem.component.TiflyBackground
import com.example.core.designsystem.theme.Purple10
import com.example.core.designsystem.theme.TiflyTheme
import com.example.feature.signs.SignItem


@Composable
fun SearchRoute(
    onSignClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val searchText by viewModel.searchText.collectAsState()
    val signList by viewModel.signs.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Purple10,
                focusedBorderColor = Purple10,

                ),
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Recherche") },
            maxLines= 1,
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {

            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(signList) { sign ->
                    SignItem(
                        word = sign.mot_fr.toString(),
                        onClick = { sign.id?.let { it1 -> onSignClick(it1) } }
                    )

                }
            }
        }
    }

}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {

        SearchTextField()

    }


}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier
) {


    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("") },
        )

    }


}


@Preview
@Composable
fun PreviewSearchScreen(){

    TiflyTheme {
        TiflyBackground {

        }
    }

}


