package com.myapplication.data.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListOfCoinNameDto (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinNameDto? = null
)