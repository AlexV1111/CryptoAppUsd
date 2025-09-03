package com.myapplication.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.myapplication.data.api.ApiService
import com.myapplication.data.database.CoinPriceInfoDao
import com.myapplication.data.mappers.DtoMapper
import com.myapplication.data.mappers.JsonMapper
import kotlinx.coroutines.delay

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

                Log.d("TEST_OF_LOADING_DATA", "Success: $coinNames")

                val fullPriceResponse = api.getFullPriceList(fSyms = coinNames)
                val jsonToDto = jsonMapper.parseCoinPriceInfoList(fullPriceResponse)
                val dtoToDbModel = dtoMapper.mapListDtoToDbModel(jsonToDto)
                coinPriceInfoDao.insertPriceList(dtoToDbModel)

                Log.d("TEST_OF_LOADING_DATA", "Successfully loaded ${dtoToDbModel.size} coins")
            } catch (e: Exception) {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${e.message}")
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


}