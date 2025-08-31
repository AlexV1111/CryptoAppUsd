package com.myapplication.data.worker

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.myapplication.data.api.ApiFactory
import com.myapplication.data.database.AppDatabase
import com.myapplication.data.mappers.DtoMapper
import com.myapplication.data.mappers.JsonMapper
import kotlinx.coroutines.delay

class SyncWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val coinPriceInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()

    private val dtoMapper = DtoMapper()
    private val jsonMapper = JsonMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoinsResponse = ApiFactory.apiService.getTopCoinsInfo(limit = 50)

                val coinNames = topCoinsResponse.data
                    ?.mapNotNull { it.coinInfo?.name }
                    ?.takeIf { it.isNotEmpty() }
                    ?.joinToString(",")
                    ?: throw Exception("Empty coin list received")

                Log.d("TEST_OF_LOADING_DATA", "Success: $coinNames")

                val fullPriceResponse = ApiFactory.apiService.getFullPriceList(fSyms = coinNames)
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