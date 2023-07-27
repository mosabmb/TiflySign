package com.example.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.core.model.data.Category

class CategoryPreviewParameterProvider : PreviewParameterProvider<List<Category>> {
    override val values: Sequence<List<Category>>
        get() = sequenceOf(
            listOf(
                Category(
                    id = "1",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "2",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "3",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "4",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "5",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "6",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "7",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "8",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "9",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "10",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
                Category(
                    id = "11",
                    name = "Category",
                    description = "Sample category description",
                    wordsCount = 10,
                    image = null
                ),
            ),
        )
}
