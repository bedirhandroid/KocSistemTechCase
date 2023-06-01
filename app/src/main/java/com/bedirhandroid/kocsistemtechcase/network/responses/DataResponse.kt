package com.bedirhandroid.kocsistemtechcase.network.responses

data class DataResponse(
    val resultCount: Int? = null,
    val results: ArrayList<DataModel>
)