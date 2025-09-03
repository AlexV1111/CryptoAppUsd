package com.myapplication.domain.usecase

import androidx.lifecycle.LiveData
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.CoinPriceInfoRepository
import javax.inject.Inject

class GetPriceInfoAboutCoinUseCase @Inject constructor(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun getPriceInfoAboutCoin(fromSymbol: String): LiveData<CoinPriceInfo> =
        coinPriceInfoRepository.getPriceInfoAboutCoin(fromSymbol)

}