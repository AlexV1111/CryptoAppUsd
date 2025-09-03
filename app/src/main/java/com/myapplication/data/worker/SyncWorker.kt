package com.myapplication.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.myapplication.data.api.ApiService
import com.myapplication.data.database.CoinPriceInfoDao
import com.myapplication.data.mappers.DtoMapper
import com.myapplication.data.mappers.JsonMapper
import kotlinx.coroutines.delay
import javax.inject.Inject

class SyncWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinPriceInfoDao: CoinPriceInfoDao,
    private val dtoMapper: DtoMapper,
    private val jsonMapper: JsonMapper,
    private val api: ApiService
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoinsResponse = api.getTopCoinsInfo(limit = 50)

                val coinNames = topCoinsResponse.data
                    ?.mapNotNull { it.coinInfo?.name }
                    ?.takeIf { it.isNotEmpty() }
                    ?.joinToString(",")
                    ?: throw Exception("Empty coin list received")

                val fullPriceResponse = api.getFullPriceList(fSyms = coinNames)
                val jsonToDto = jsonMapper.parseCoinPriceInfoList(fullPriceResponse)
                val dtoToDbModel = dtoMapper.mapListDtoToDbModel(jsonToDto)
                coinPriceInfoDao.insertPriceList(dtoToDbModel)
            } catch (e: Exception) {
                throw e
            }
            delay(10_000)
        }
    }

    companion object {
        const val NAME = "SyncWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<SyncWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val coinPriceInfoDao: CoinPriceInfoDao,
        private val dtoMapper: DtoMapper,
        private val jsonMapper: JsonMapper,
        private val api: ApiService
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return SyncWorker(
                context,
                workerParameters,
                coinPriceInfoDao,
                dtoMapper,
                jsonMapper,
                api
            )
        }

    }


}