package com.application.punkapi.beer.entity

import com.google.gson.annotations.SerializedName

data class Temp(

    @SerializedName("value")
    val value : Int,
    @SerializedName("unit")
    val unit : String,
)
