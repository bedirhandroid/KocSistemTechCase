package com.bedirhandroid.kocsistemtechcase.ui.fragments.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.ErrorMessages
import com.bedirhandroid.kocsistemtechcase.base.Repo
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalListViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

}