package com.bedirhandroid.kocsistemtechcase.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bedirhandroid.kocsistemtechcase.network.ApiService
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import java.io.IOException
import javax.inject.Inject


class PagingListAdapter @Inject constructor(private val apiService: ApiService, private val term: String) :
    PagingSource<Int, DataModel>() {
    override fun getRefreshKey(state: PagingState<Int, DataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataModel> {
        val position = params.key ?: 0
        val limit = params.loadSize
        return try {
            val response = apiService.getList(term, position, limit)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 0) null else position,
                nextKey = if (response.results.isEmpty()) null else position + 20
            )
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        }
    }

}