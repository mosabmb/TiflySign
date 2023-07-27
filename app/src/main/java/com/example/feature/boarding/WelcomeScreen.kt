package com.example.feature.boarding


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.*


@OptIn(ExperimentalPagerApi::class)
@ExperimentalAnimationApi
@Composable
fun WelcomeScreen(
    onButtomClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
        HorizontalPager(
            modifier = modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        FinishButton(
            pagerState = pagerState
        ) {
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


        Spacer(modifier = modifier.size(50.dp))
        Column(
            modifier = modifier.width(280.dp).height(280.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            GlideImage(
                model = onBoardingPage.image,
                contentDescription = "sd"
            )
        }

        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight(500),
            lineHeight= 40.sp,
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,

            )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp, vertical= 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {


            Button(
                modifier = Modifier
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(Color("#60cdcd".toColorInt())),
                onClick = onClick,
            ) {
                Text(text = "Explorer", color = Color.White )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}
