package com.example.betterhrcodetest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.betterhrcodetest.R
import com.example.betterhrcodetest.application.App
import com.example.betterhrcodetest.dagger.ViewModelFactory
import com.example.betterhrcodetest.viewmodel.HomeViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}