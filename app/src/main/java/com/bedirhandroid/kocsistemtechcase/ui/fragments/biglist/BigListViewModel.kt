package com.bedirhandroid.kocsistemtechcase.ui.fragments.biglist

import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BigListViewModel @Inject constructor(private val repo: Repo): BaseViewModel() {
    val localData = LocalDataManager.getInstance().localListData
}