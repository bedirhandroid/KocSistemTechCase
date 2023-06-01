package com.bedirhandroid.kocsistemtechcase.network

import com.bedirhandroid.kocsistemtechcase.network.responses.DataResponse
import com.bedirhandroid.kocsistemtechcase.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getList(
        @Query("term") term: String,
        @Query("offset") page: Int,
        @Query("limit") limit: Int = 20,
    ) : DataResponse

    @GET("search")
    suspend fun getStaticList(
        @Query("term") term: String = Constant.KEY_DEFAULT_QUERY,
    ) : DataResponse?
}