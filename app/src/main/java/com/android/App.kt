package com.android

import android.app.Application
import com.android.hayahpharma.uitls.MySharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
    }
}