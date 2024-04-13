package com.dqc.wanandroid

import android.app.Application
import com.dqc.base.baseModule
import com.dqc.home.homeModules
import com.google.android.material.color.DynamicColors
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

            modules(appModule)
            modules(baseModule)
            modules(homeModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}