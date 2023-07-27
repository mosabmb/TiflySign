package com.example.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.component.TiflyGradientBackground
import com.example.core.designsystem.theme.Purple80
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography

@Composable
fun SharingIsCaringCard(
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.padding(25.dp),
        shape = RoundedCornerShape(20.dp),
    ) {

        Column(
            modifier = modifier.padding(20.dp)
        ) {

            Row {
                Text(
                    text = "Quelqu'un que vous connaissez\npourrait vouloir apprendre aussi :)",
                    style = TiflyTypography.bodyLarge,
                    color = Purple80,
                    fontSize = 17.sp
                )
            }

            Box(
                modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Yellow),
                    onClick = { /* Do something! */ },
                ) {
                    Text(
                        text = "Partage, c’est l’entraide",
                        fontSize = 15.sp,
                    )
                }

            }
        }
    }

}



@Preview
@Composable
fun PreviewSharingIsCaringCard() {
    TiflyTheme {
        TiflyGradientBackground {
            SharingIsCaringCard()
        }
    }
}