package com.example.feature.home.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.core.designsystem.component.TiflyGradientBackground
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography
import com.example.tiflysignapp.R

@Composable
fun WelcomeCard(
    modifier: Modifier = Modifier
) {

    Cover(
        painterResource(id = R.drawable.people),
    )

}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WelcomeCardImage(
    modifier: Modifier = Modifier

){

    Box(

        modifier = modifier.background(Color.Transparent),
    ) {

        GlideImage(
            model = R.drawable.student1,
            contentDescription = "sd",
            contentScale= ContentScale.FillWidth,
        )
    }


}



@Preview
@Composable
fun PreviewWelcomeCard() {
    TiflyTheme {
        TiflyGradientBackground {
            WelcomeCard()
        }
    }
}

@Composable
fun Cover(
    painter: Painter,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth().padding(top = 30.dp),

    ) {

        Box(
            modifier = modifier.height(200.dp)
        ) {

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                        )
                    )
            ){

            }
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(18.dp),
                contentAlignment = Alignment.BottomStart
            ){

                Text(
                    text = "Faire apprendre\nla langue des signes\nà nos enfants sourds",
                    style = TiflyTypography.bodyMedium,
                    fontSize = 23.sp,
                    color = Color.White,
                    lineHeight = 26.sp
                )

            }


        }

    }

}