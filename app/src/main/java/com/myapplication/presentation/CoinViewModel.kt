package com.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.usecase.GetPriceInfoAboutCoinUseCase
import com.myapplication.domain.usecase.GetPriceListUseCase
import com.myapplication.domain.usecase.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getPriceListUseCase: GetPriceListUseCase,
    private val getPriceInfoAboutCoinUseCase: GetPriceInfoAboutCoinUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    init {
        loadDataUseCase.loadDataUseCase()
    }

    fun priceList(): LiveData<List<CoinPriceInfo>> {
        return getPriceListUseCase.getPriceList()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return getPriceInfoAboutCoinUseCase.getPriceInfoAboutCoin(fSym)
    }

}