package com.myapplication.di

import android.app.Application
import com.myapplication.CoinApp
import com.myapplication.presentation.CoinDetailFragment
import com.myapplication.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}