package com.myapplication.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.myapplication.data.mappers.DbModelMapper
import com.myapplication.data.worker.SyncWorker
import com.myapplication.domain.CoinPriceInfo
import com.myapplication.domain.CoinPriceInfoRepository
import javax.inject.Inject

class CoinPriceInfoRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinPriceInfoDao: CoinPriceInfoDao,
    private val dbModelMapper: DbModelMapper
) : CoinPriceInfoRepository {

    private val workManager = WorkManager.getInstance(application)

    override fun getPriceList(): LiveData<List<CoinPriceInfo>> {
        return MediatorLiveData<List<CoinPriceInfo>>().apply {
            addSource(coinPriceInfoDao.getPriceList()) {
                value = dbModelMapper.mapListDbModelToEntity(it)
            }
        }
    }

    override fun getPriceInfoAboutCoin(fromSymbol: String): LiveData<CoinPriceInfo> {
        val dbModel = coinPriceInfoDao.getPriceInfoAboutCoin(fromSymbol)
        return MediatorLiveData<CoinPriceInfo>().apply {
            addSource(dbModel) {
                value = dbModelMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun loadData() {
        workManager.enqueueUniqueWork(
            SyncWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            SyncWorker.makeRequest()
        )
    }

}