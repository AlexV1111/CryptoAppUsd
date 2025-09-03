package com.myapplication.domain.usecase

import androidx.lifecycle.LiveData
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.CoinPriceInfoRepository
import javax.inject.Inject

class GetPriceListUseCase @Inject constructor(private val coinPriceInfoRepository: CoinPriceInfoRepository) {

    fun getPriceList(): LiveData<List<CoinPriceInfo>> = coinPriceInfoRepository.getPriceList()
}