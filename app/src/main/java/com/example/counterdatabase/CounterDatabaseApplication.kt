package com.example.counterdatabase

import android.app.Application
import com.example.counterdatabase.data.RetrofitInstance

class CounterDatabaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitInstance.initialize(this)
    }
}

