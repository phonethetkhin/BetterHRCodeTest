package com.example.betterhrcodetest.dagger

import com.example.betterhrcodetest.ui.activity.MainActivity
import com.example.betterhrcodetest.ui.fragment.LaunchDetailsFragment
import com.example.betterhrcodetest.ui.fragment.LaunchListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Modules::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(launchListFragment: LaunchListFragment)
    fun inject(launchDetailsFragment: LaunchDetailsFragment)
}