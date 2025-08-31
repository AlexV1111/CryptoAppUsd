package com.myapplication.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.myapplication.data.database.CoinPriceInfoRepositoryImpl
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.usecase.GetPriceInfoAboutCoinUseCase
import com.myapplication.domain.usecase.GetPriceListUseCase
import com.myapplication.domain.usecase.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinPriceInfoRepositoryImpl(application)

    private val getPriceListUseCase = GetPriceListUseCase(repository)
    private val getPriceInfoAboutCoinUseCase = GetPriceInfoAboutCoinUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

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