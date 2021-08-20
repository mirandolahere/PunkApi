package com.application.punkapi.beer.entity

import com.google.gson.annotations.SerializedName

data class Fermentation (

    @SerializedName("temp")
    val temp : Temp
)