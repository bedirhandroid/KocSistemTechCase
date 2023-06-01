package com.bedirhandroid.kocsistemtechcase.ui.activities.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(private val repo : Repo) : BaseViewModel() {

    val mutableQueryKey = MutableLiveData<String>()
}