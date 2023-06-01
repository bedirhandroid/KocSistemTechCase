package com.bedirhandroid.kocsistemtechcase.ui.activities.search

import android.content.Intent
import android.widget.SearchView
import com.bedirhandroid.kocsistemtechcase.base.BaseActivity
import com.bedirhandroid.kocsistemtechcase.databinding.ActivitySearchBinding
import com.bedirhandroid.kocsistemtechcase.ui.activities.main.MainActivity
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import com.bedirhandroid.kocsistemtechcase.util.observerNotNull

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchActivityViewModel>() {
    override fun initView() {}

    override fun initListeners() {
        viewBindingScope {
            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.replace(" ", "+")
                        ?.let(viewModel.mutableQueryKey::postValue)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean { return false }
            })
        }
    }

    private val queryObserver = observerNotNull<String> {
        LocalDataManager.getInstance().localKey = it
        Intent(this, MainActivity::class.java).also { _intent ->
            startActivity(_intent)
        }
    }

    override fun initObservers() {
        viewModel.mutableQueryKey.observe(this, queryObserver)
    }
}