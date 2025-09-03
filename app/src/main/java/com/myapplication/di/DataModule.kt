package com.myapplication.di

import android.app.Application
import android.content.Context
import com.myapplication.data.api.ApiFactory
import com.myapplication.data.api.ApiService
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
    @ApplicationScope
    fun bindCoinPriceInfoRepository(impl: CoinPriceInfoRepositoryImpl): CoinPriceInfoRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideCoinPriceInfoDao(application: Application): CoinPriceInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }

}