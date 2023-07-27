package com.example.feature.signs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.model.data.Sign
import com.example.core.ui.DevicePreviews
import com.example.core.ui.SignPreviewParameterProvider
import com.example.core.ui.TrackScrollJank
import com.example.tiflysignapp.R

@Composable
internal fun SignsRoute(
    category: String,
    onSignClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignsViewModel
) {

    val signUiState by viewModel.uiState.collectAsStateWithLifecycle()

    SignsScreen(
        signsUiState = signUiState,
        modifier = modifier,
        category = viewModel.category,
        onSignClick = onSignClick
    )

}

@Composable
internal fun SignsScreen(
    category: String,
    onSignClick: (String) -> Unit,
    signsUiState: SignsUiState,
    modifier: Modifier = Modifier,
) {

    val state = rememberLazyListState()
    TrackScrollJank(scrollableState = state, stateName = "signs:screen")

    LazyColumn(
        state = state,
        modifier = modifier.fillMaxWidth().padding(start = 25.dp , end  = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(13.dp),
    ) {

        when (signsUiState) {
            is SignsUiState.Signs -> {
                signsBody(
                    signs = signsUiState,
                    category = category,
                    onSignClick = onSignClick,
                    modifier = modifier
                )
            }
            SignsUiState.Loading -> {}
            SignsUiState.Empty -> {}
        }
    }
}


@Composable
private fun SignsHeader(modifier: Modifier, category: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
    ) {

        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = category,
                    fontSize = 30.sp
                )
                Text(
                    modifier = modifier.padding(top = 10.dp),
                    text = "Découvrir les signes\npar catégories",
                    fontSize = 16.sp,
                )

                Button(
                    modifier = modifier.padding(top = 15.dp, bottom = 40.dp),
                    onClick = { },
                ) {

                    Text(
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Recherche",
                        fontSize = 17.sp
                    )
                }
            }

            Box(
                modifier = modifier.fillMaxWidth().fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painterResource(R.drawable.noun_sign_1),
                    contentDescription = null,
                )
            }
        }
    }
}

private fun LazyListScope.signsBody( modifier: Modifier, signs: SignsUiState, category: String, onSignClick: (String) -> Unit) {

    item {
        SignsHeader(
            category = category,
            modifier = modifier
        )
    }
    signListCards ( modifier =  modifier , signs , onSignClick = onSignClick, )

}

fun LazyListScope.signListCards( modifier: Modifier, signs: SignsUiState , onSignClick: (String) -> Unit) {

    when (signs) {

        is SignsUiState.Signs -> {
            signListCardsItems(
                items = signs.signs,
                onSignClick = onSignClick,
                modifier = modifier
            )
        }

        is SignsUiState.Loading -> {

        }

        is SignsUiState.Empty -> {

        }
    }

}

private fun LazyListScope.signListCardsItems( modifier: Modifier, items: List<Sign>, onSignClick: (String) -> Unit
) = items (

    items = items,
    key = { it.id!! },
    itemContent = { sign ->
        sign.mot_fr?.let {
            SignItem (
                    modifier = modifier,
                    word = it,
                    onClick = { sign.id?.let { it1 -> onSignClick(it1) } },
            )

        }
    },

)

@DevicePreviews
@Composable
fun SignsScreenPopulated(
    @PreviewParameter(SignPreviewParameterProvider::class) signs: List<Sign>
) {
    TiflyTheme {
        SignsScreen(
            signsUiState = SignsUiState.Signs(signs = signs),
            category = "Category",
            onSignClick = {}
        )
    }
}

