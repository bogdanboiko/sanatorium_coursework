package com.example.medappcoursework

import android.app.Application
import com.example.medappcoursework.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(moduleList)
        }
    }
}