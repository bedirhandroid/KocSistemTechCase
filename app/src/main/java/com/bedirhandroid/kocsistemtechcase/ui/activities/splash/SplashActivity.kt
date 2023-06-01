package com.bedirhandroid.kocsistemtechcase.ui.activities.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.bedirhandroid.kocsistemtechcase.base.BaseActivity
import com.bedirhandroid.kocsistemtechcase.databinding.ActivitySplashBinding
import com.bedirhandroid.kocsistemtechcase.ui.activities.main.MainActivity
import com.bedirhandroid.kocsistemtechcase.ui.activities.search.SearchActivity
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {
    override fun initView() {
        binding.lvAnim.playAnimation()
        dynamicNavigate()
    }

    private fun dynamicNavigate() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (LocalDataManager.getInstance().localKey.isNullOrBlank()) {
                true -> SearchActivity::class.java
                else -> MainActivity::class.java
            }.let {
                Intent(this, it).also { _intent ->
                    startActivity(_intent)
                    finish()
                }
            }
        }, 2000)
    }

    override fun initListeners() {}

    override fun initObservers() {}

}