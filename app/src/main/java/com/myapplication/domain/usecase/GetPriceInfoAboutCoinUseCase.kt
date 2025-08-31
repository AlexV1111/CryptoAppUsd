package com.myapplication.domain.usecase

import androidx.lifecycle.LiveData
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.CoinPriceInfoRepository

class GetPriceInfoAboutCoinUseCase(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun getPriceInfoAboutCoin(fromSymbol: String): LiveData<CoinPriceInfo> =
        coinPriceInfoRepository.getPriceInfoAboutCoin(fromSymbol)

}