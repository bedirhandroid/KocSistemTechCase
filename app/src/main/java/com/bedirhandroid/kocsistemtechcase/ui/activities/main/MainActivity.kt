package com.bedirhandroid.kocsistemtechcase.ui.activities.main

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseActivity
import com.bedirhandroid.kocsistemtechcase.databinding.ActivityMainBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.ui.activities.search.SearchActivity
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import com.bedirhandroid.kocsistemtechcase.util.showAlert
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {
    override fun initView() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard,
                R.id.navigation_local_list,
                R.id.navigation_grid_list,
                R.id.navigation_big_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun initListeners() {
        viewBindingScope {
            btnDropDatabase.setOnClickListener {
                showAlert(getString(R.string.drop_local_data), getString(R.string.attention)) {
                    LocalDataManager.getInstance().clearData()
                    Intent(this@MainActivity, SearchActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }
    }

    private val staticListObserver = Observer<ArrayList<DataModel>> {
        LocalDataManager.getInstance().localListData = it
    }

    override fun initObservers() {
        viewModelScope {
            staticLiveData.observe(this@MainActivity, staticListObserver)
        }
    }
}