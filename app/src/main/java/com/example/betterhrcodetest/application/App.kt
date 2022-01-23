package com.example.betterhrcodetest.application

import android.app.Application
import com.example.betterhrcodetest.dagger.AppComponent
import com.example.betterhrcodetest.dagger.DaggerAppComponent
import com.example.betterhrcodetest.dagger.Modules

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .modules(Modules(this))
            .build()
    }
}
