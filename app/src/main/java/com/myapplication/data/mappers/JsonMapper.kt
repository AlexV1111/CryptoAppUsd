package com.myapplication.data.mappers

import com.google.gson.Gson
import com.myapplication.data.DTO.CoinPriceInfoDto
import com.myapplication.data.DTO.CoinPriceInfoJsonObjectDto
import javax.inject.Inject

class JsonMapper @Inject constructor() {
    fun parseCoinPriceInfoList(jsonObject: CoinPriceInfoJsonObjectDto): List<CoinPriceInfoDto> {
        val result = mutableListOf<CoinPriceInfoDto>()
        val jsonObject = jsonObject.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

}