package com.example.feature.boarding

import androidx.annotation.DrawableRes
import com.example.tiflysignapp.R


sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.lotus,
        title = "Guide pratique pour l'apprentissage de la LST",
        description = "TiFly Sign est un guide destiné aux parents pour aider leurs enfants malentendants à apprendre la langue des signes tunisienne."
    )

    object Second : OnBoardingPage(
        image = R.drawable.search,
        title = "Rechercher des signes de manière intuitive",
        description = "Les parents sont censés rechercher les signes désirés en tapant les mots qui leur correspondent"
    )


    object Third : OnBoardingPage(
        image = R.drawable.tap,
        title = "Représentation des signes d'une manière accessible à tous",
        description = "Chaque signe est présenté sous différents formats pour en faciliter sa compréhension."
    )


}