package com.myapplication.domain.usecase

import androidx.lifecycle.LiveData
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.CoinPriceInfoRepository

class GetPriceListUseCase(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun getPriceList(): LiveData<List<CoinPriceInfo>> = coinPriceInfoRepository.getPriceList()
}