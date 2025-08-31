package com.myapplication.domain

import androidx.lifecycle.LiveData

interface CoinPriceInfoRepository {

    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    fun getPriceInfoAboutCoin(fromSymbol: String): LiveData<CoinPriceInfo>

    fun loadData()
}