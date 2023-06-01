package com.bedirhandroid.kocsistemtechcase.network

import com.bedirhandroid.kocsistemtechcase.network.responses.DataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getList(
        @Query("term") term: String,
        @Query("offset") page: Int,
        @Query("limit") limit: Int,
    ) : DataResponse
}