package com.bedirhandroid.kocsistemtechcase.ui.fragments.dashboard

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseFragment
import com.bedirhandroid.kocsistemtechcase.databinding.FragmentDashboardBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.paging.PagingLoadStateAdapter
import com.bedirhandroid.kocsistemtechcase.ui.adapter.ListAdapter
import com.bedirhandroid.kocsistemtechcase.util.Constant.KEY_DEFAULT_QUERY
import com.bedirhandroid.kocsistemtechcase.util.Constant.KEY_DETAIL_DATA
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import com.bedirhandroid.kocsistemtechcase.util.navigateWithBundleTo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    private lateinit var listAdapter: ListAdapter
    override fun initView() {
        listAdapter = ListAdapter(::onClickItem)
        binding.rvList.adapter = listAdapter.withLoadStateFooter(
            footer = PagingLoadStateAdapter(listAdapter)
        )
    }

    override fun initListeners() {}

    override fun initObservers() {
        viewModelScope {
            viewLifecycleOwner.lifecycleScope.launch {
                getList(
                    LocalDataManager.getInstance().localKey ?: KEY_DEFAULT_QUERY
                ).collectLatest { _data ->
                    listAdapter.submitData(_data)
                }
            }
        }
    }

    private fun onClickItem(data: DataModel) {
        Bundle().apply {
            putSerializable(KEY_DETAIL_DATA, data)
        }.also {
            navigateWithBundleTo(R.id.action_navigation_dashboard_to_detailFragment, it)
        }
    }
}