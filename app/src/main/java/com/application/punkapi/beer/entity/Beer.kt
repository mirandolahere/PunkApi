package com.application.punkapi.beer.entity

import com.google.gson.annotations.SerializedName

data class Beer (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("first_brewed")
    val first_brewed: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("abv")
    val ABV : Double,
    @SerializedName("method")
    val method : Method,

    )