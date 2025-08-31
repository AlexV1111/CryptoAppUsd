package com.myapplication.domain.usecase

import com.myapplication.domain.CoinPriceInfoRepository

class LoadDataUseCase(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun loadDataUseCase() = coinPriceInfoRepository.loadData()

}