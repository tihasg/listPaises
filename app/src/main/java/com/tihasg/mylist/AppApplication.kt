package com.tihasg.mylist

import android.app.Application
import android.content.Context
import com.tihasg.mylist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppApplication)
            androidFileProperties()

            modules(
                viewModelModule
            )
        }

    }
    companion object {
        lateinit var instance: Context
    }

}