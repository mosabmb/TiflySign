package com.example.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.core.designsystem.component.TiflyBackground
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography
import com.example.core.model.data.Category
import com.example.core.ui.CategoryPreviewParameterProvider
import com.example.tiflysignapp.R


@Composable
fun CategoryCardListItem(
    category: Category,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.height(100.dp).height(100.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Box(
            modifier = modifier.padding().fillMaxWidth().clickable { },
            contentAlignment = Alignment.BottomStart
        ) {

            Image(
                painter = painterResource(id = R.drawable.imgg),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            /*
            GlideImage(
                model = painterResource(id = R.drawable.student1),
                contentDescription = "sd",
                contentScale= ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )*/

            Row(
                modifier = modifier.height(30.dp).background(Color.White).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(start = 20.dp),
                    text = category.name.toString(),
                    color = Color.Black,
                    fontStyle = TiflyTypography.bodyMedium.fontStyle
                )
            }
        }
    }
}

@Composable
fun CategoriesHorizontal(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        state = rememberLazyListState(),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),

        ) {
        items( items = categories)
        { category ->
            CategoryCardListItem( category = category)
        }
    }

}

@Preview
@Composable
fun PreviewCategoriesHorizontalScroll(@PreviewParameter(CategoryPreviewParameterProvider::class) categories: List<Category>) {
    TiflyTheme {
        TiflyBackground {
            Row {
                CategoriesHorizontal(categories)
            }
        }
    }
}