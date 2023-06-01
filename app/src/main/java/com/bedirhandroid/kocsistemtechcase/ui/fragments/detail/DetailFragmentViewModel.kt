package com.bedirhandroid.kocsistemtechcase.ui.fragments.detail

import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(private val repo: Repo) : BaseViewModel()  {
}