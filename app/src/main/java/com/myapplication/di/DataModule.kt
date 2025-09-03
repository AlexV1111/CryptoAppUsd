package com.myapplication.di

import android.app.Application
import android.content.Context
import com.myapplication.data.database.AppDatabase
import com.myapplication.data.database.CoinPriceInfoDao
import com.myapplication.data.database.CoinPriceInfoRepositoryImpl
import com.myapplication.domain.CoinPriceInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinPriceInfoRepository(impl: CoinPriceInfoRepositoryImpl): CoinPriceInfoRepository

    companion object {
        @Provides
        fun provideCoinPriceInfoDao(application: Application): CoinPriceInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }

}