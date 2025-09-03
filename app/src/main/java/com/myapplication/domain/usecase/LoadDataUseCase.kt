package com.myapplication.domain.usecase

import com.myapplication.domain.CoinPriceInfoRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun loadDataUseCase() = coinPriceInfoRepository.loadData()

}