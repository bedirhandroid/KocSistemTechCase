package com.bedirhandroid.kocsistemtechcase.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bedirhandroid.kocsistemtechcase.network.ApiService
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.paging.PagingListAdapter
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiService : ApiService) {

    fun getList(term: String) : Flow<PagingData<DataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingListAdapter(apiService, term) }
        ).flow
    }


}