package com.example.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.component.TiflyGradientBackground
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography

@Composable
fun SectionTitleDivider(
    title: String,
    modifier : Modifier = Modifier
) {

    Box ( modifier.fillMaxWidth().padding(start = 25.dp, end = 25.dp, bottom = 10.dp) ) {
        Text(
            text = title,
            style = TiflyTypography.titleLarge,
        )
        Icon(
            Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = modifier.align(Alignment.CenterEnd).size(20.dp)

        )
    }
}

@Preview
@Composable
fun PreviewSectionTitleDivider() {
    TiflyTheme {
        TiflyGradientBackground {
            SectionTitleDivider(
                "Categories"
            )
        }
    }
}