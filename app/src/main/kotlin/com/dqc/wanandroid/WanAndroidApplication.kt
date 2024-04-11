package com.dqc.wanandroid

import android.app.Application
import com.dqc.base.baseModule
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.utilities.DynamicColor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class WanAndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
        initDynamicColorScheme()
    }

    private fun initDynamicColorScheme() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }


    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@WanAndroidApplication)

            modules(baseModule)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}