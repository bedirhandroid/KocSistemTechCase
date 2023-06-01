package com.bedirhandroid.kocsistemtechcase.ui.fragments.dashboard

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.Repo
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {

    fun getList(query: String): Flow<PagingData<DataModel>> {
        return repo.getList(query).cachedIn(viewModelScope)
    }
}
