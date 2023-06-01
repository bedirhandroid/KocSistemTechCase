package com.bedirhandroid.kocsistemtechcase

import android.app.Application
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KocSistemTechCaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startLocalDataManager()
    }

    private fun startLocalDataManager() {
        LocalDataManager.init(applicationContext)
    }
}