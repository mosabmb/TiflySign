package com.example.feature.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.ui.TrackScrollJank
import com.example.feature.home.components.SectionTitleDivider
import com.example.feature.home.components.SharingIsCaringCard
import com.example.feature.home.components.slider.AutoSlidingCarousel
import com.example.feature.home.components.slider.images
import com.example.feature.signs.SignsUiState
import com.example.feature.signs.SignsViewModel
import com.example.feature.signs.signListCards
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
fun HomeRoute(
    onSignClick: (String) -> Unit,
    viewModel: SignsViewModel = hiltViewModel()
) {

    val signUiState by viewModel.uiState1.collectAsStateWithLifecycle()

    HomeScreen(
        onSignClick = onSignClick,
        signsUiState = signUiState
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    onSignClick: (String) -> Unit,
    signsUiState: SignsUiState,
    modifier: Modifier = Modifier,
) {

    val state = rememberLazyListState()

    TrackScrollJank(scrollableState = state, stateName = "signs:screen")

    LazyColumn(
        state = state,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        item {
            AutoSlidingCarousel(
                itemsCount = images.size,
                itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index])
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(200.dp)
                    )
                }
            )
        }

        item {
            SharingIsCaringCard()
        }

        item {
            SectionTitleDivider("Les Plus Utilisés")
        }


        when (signsUiState) {

            is SignsUiState.Signs -> {
                signListCards(
                    signs = signsUiState,
                    onSignClick = onSignClick,
                    modifier = modifier.padding(start = 20.dp ,end = 20.dp)
                )
            }

            is SignsUiState.Loading -> {}
            is SignsUiState.Empty -> {}


        }


    }
}


