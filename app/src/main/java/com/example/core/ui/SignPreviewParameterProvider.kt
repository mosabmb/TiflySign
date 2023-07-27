package com.example.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.core.model.data.Sign

class SignPreviewParameterProvider : PreviewParameterProvider<List<Sign>> {
    override val values: Sequence<List<Sign>>
        get() = sequenceOf(
            listOf(
                Sign(
                    id = "1",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "2",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "3",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "4",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "5",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "6",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "7",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "8",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                ),
                Sign(
                    id = "9",
                    mot_fr = "Bonjour",
                    mot_ar = "Sbahlkhyr",
                )

            ),
        )
}
