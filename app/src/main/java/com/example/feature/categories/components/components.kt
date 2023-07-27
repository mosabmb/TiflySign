package com.example.feature.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.core.designsystem.component.TiflyBackground
import com.example.core.designsystem.theme.TiflyTheme
import com.example.core.designsystem.theme.TiflyTypography
import com.example.core.model.data.Category
import com.example.core.ui.CategoryPreviewParameterProvider


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryCardGridItem(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.padding(0.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        //border = BorderStroke(0.5.dp,Color.Gray),
        // colors = CardDefaults.cardColors(containerColor = Color.Transparent,),
    ) {
        Box(
            modifier = modifier
                .height(160.dp)
                .padding()
                .clickable { onClick() },
            contentAlignment = Alignment.BottomEnd
        ) {
            /*
            Image(
                painter = painterResource(id = R.drawable.imgg),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )*/

            GlideImage(
                model = category.image,
                contentDescription = "sd",
                contentScale= ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(start = 20.dp),
                    text = category.name.toString(),
                    color = Color.Black,
                    fontStyle = TiflyTypography.titleSmall.fontStyle
                )
            }
        }
    }
}

@Composable
fun CategoriesGrid(
    onClick: (String) -> Unit,
    categories: List<Category>,
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(25.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        gridItems(
            data = categories,
            columnCount = 2,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 0.dp)
        ) { itemData ->
            CategoryCardGridItem(
                itemData,
                onClick = { itemData.name?.let { onClick(it) } }
            )
        }
    }

}

fun <T> LazyListScope.gridItems(
    data: List<T>,
    columnCount: Int,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = { it.hashCode() }) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCategoryGrid(@PreviewParameter(CategoryPreviewParameterProvider::class) categories: List<Category>) {
    TiflyTheme {
        TiflyBackground {
            Row {
                CategoriesGrid({},categories)
            }
        }
    }
}

