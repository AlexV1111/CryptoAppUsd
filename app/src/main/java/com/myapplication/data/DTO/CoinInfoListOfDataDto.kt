package com.myapplication.data.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoListOfDataDto (
    @SerializedName("Data")
    @Expose
    val data: List<ListOfCoinNameDto>? = null
)
