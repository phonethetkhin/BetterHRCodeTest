package com.example.betterhrcodetest.dagger

import android.app.Application
import com.example.betterhrcodetest.repo.HomeRepo
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class Modules @Inject constructor(private val application: Application) {

    @Singleton
    @Provides
    internal fun provideHomeRepo() = HomeRepo(application)
}