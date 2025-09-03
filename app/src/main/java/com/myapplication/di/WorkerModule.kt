package com.myapplication.di

import com.myapplication.data.worker.ChildWorkerFactory
import com.myapplication.data.worker.SyncWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(SyncWorker::class)
    @Binds
    fun bindSyncWorkerFactory(worker: SyncWorker.Factory): ChildWorkerFactory
}