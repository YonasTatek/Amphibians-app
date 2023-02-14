package com.example.amphibiansapp.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class AmphibiansPhoto(
    val name:String,
    val type:String,
    val description:String,
    @SerialName(value = "img_src")
    val imgSrc:String,
)
