package com.demo.networkpagination.utils

import android.app.Application
import com.demo.networkpagination.BuildConfig
import com.demo.networkpagination.network.networkModule
import com.demo.networkpagination.repos.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpTimber()
        setUpKoin()
    }

    /**
     * start Timber in case of debugging.
     */
    private fun setUpTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    /**
     * initialize and start Koin.
     */
    private fun setUpKoin() {

        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger()

            androidContext(this@MyApp)

            modules(
                appModule,
                networkModule,
                repoModule
            )
        }
    }

}