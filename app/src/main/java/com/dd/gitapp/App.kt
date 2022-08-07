package com.dd.gitapp

import android.app.Application
import android.content.Context
import appModule
import com.dd.gitapp.di.AppComponent
import com.dd.gitapp.di.DaggerAppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appComponent.givUsersRepo()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val Context.app get() = applicationContext as App
