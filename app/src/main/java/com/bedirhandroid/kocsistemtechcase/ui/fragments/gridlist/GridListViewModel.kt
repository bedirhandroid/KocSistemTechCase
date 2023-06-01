package com.bedirhandroid.kocsistemtechcase.ui.fragments.gridlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GridListViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

}