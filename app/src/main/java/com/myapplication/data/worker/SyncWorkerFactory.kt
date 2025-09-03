package com.myapplication.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.myapplication.data.api.ApiService
import com.myapplication.data.database.CoinPriceInfoDao
import com.myapplication.data.mappers.DtoMapper
import com.myapplication.data.mappers.JsonMapper
import javax.inject.Inject

class SyncWorkerFactory @Inject constructor(
    private val coinPriceInfoDao: CoinPriceInfoDao,
    private val dtoMapper: DtoMapper,
    private val jsonMapper: JsonMapper,
    private val api: ApiService
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return SyncWorker(
            appContext,
            workerParameters,
            coinPriceInfoDao,
            dtoMapper,
            jsonMapper,
            api
        )
    }
}