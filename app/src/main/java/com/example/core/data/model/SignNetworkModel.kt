package com.example.core.data.model

import com.example.core.model.data.Sign

data class SignNetworkModel(
    val id : String,
    val mot_fr : String,
    val mot_ar : String,
    val description : String,
)

fun SignNetworkModel.toSign(): Sign {
    return Sign(
        id = id,
        mot_fr =  mot_fr,
        mot_ar = mot_ar,
    )
}