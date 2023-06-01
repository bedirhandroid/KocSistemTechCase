package com.bedirhandroid.kocsistemtechcase.ui.fragments.local

import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocalListViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

}