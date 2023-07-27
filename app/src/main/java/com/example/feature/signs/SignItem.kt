package com.example.feature.signs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.TiflySignAppTheme
import com.example.core.designsystem.icon.TiflyIcons
import com.example.core.designsystem.theme.TiflyTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignItem(
    word: String,
    onClick: () -> Unit,
    description: String = "",
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {

    OutlinedCard(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.7.dp,Color.Gray),
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent,),
        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClick() }
                    .padding(15.dp),
            ) {
                SignIcon(modifier = iconModifier)
                Spacer(modifier = Modifier.width(5.dp))
                SignContent(word)
            }
        }
    }



}


@Composable
fun SignContent(
    word: String,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = word,
            style = TiflyTypography.titleLarge ,
        )
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(TiflyIcons.Arrow),
            contentDescription = null,
            modifier = modifier.size(13.dp),
        )
        Spacer(modifier.size(20.dp))
    }
}

@Composable
fun SignIcon( modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.width(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painterResource(TiflyIcons.Book),
            contentDescription = null,
            modifier.size(30.dp),
        )
    }


}


@Preview
@Composable
fun SignCardPreview(){

    TiflySignAppTheme {
        Surface {
            SignItem(
                "Bonjour",
                onClick = {},
                description = "Sample word description"
            )
        }

    }
}