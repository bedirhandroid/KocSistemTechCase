package com.bedirhandroid.kocsistemtechcase.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.kocsistemtechcase.base.BaseViewModel
import com.bedirhandroid.kocsistemtechcase.base.ErrorMessages
import com.bedirhandroid.kocsistemtechcase.base.Repo
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repo: Repo) : BaseViewModel() {
    private val mutableStaticList = MutableLiveData<ArrayList<DataModel>>()
    val staticLiveData: LiveData<ArrayList<DataModel>> get() = mutableStaticList

    init {
        getStaticList()
    }

    private fun getStaticList() {
        sendRequest {
            LocalDataManager.getInstance().localListData?.let {
                mutableStaticList.postValue(it)
            } ?: kotlin.run {
                repo.getStaticList().collect {
                    it?.let { _data -> mutableStaticList.postValue(_data.results) } ?: kotlin.run {
                        errorLiveData.postValue(ErrorMessages.ERROR)
                    }
                }
            }
        }
    }
}