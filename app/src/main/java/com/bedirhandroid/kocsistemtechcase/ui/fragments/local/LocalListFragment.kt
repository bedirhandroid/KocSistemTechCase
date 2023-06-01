package com.bedirhandroid.kocsistemtechcase.ui.fragments.local

import DynamicLayoutAdapter
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseFragment
import com.bedirhandroid.kocsistemtechcase.databinding.FragmentLocalListBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.*

class LocalListFragment : BaseFragment<FragmentLocalListBinding, LocalListViewModel>() {

    private lateinit var localListAdapter: DynamicLayoutAdapter
    override fun initView() {
        LocalDataManager.getInstance().localListData?.let {
            localListAdapter = DynamicLayoutAdapter(
                it,
                1,
                ::onClickItem
            )
            binding.apply {
                rvLocal.adapter = localListAdapter
                txtListCount.text = getString(R.string.dynamic_list_count, it.size)
            }
        } ?: kotlin.run {
            showEmptyList()
        }
    }

    override fun initListeners() {}

    override fun initObservers() {}

    private fun onClickItem(data: DataModel) {
        Bundle().apply {
            putSerializable(Constant.KEY_DETAIL_DATA, data)
        }.also {
            navigateWithBundleTo(R.id.action_navigation_local_list_to_detailFragment, it)
        }
    }

    private fun showEmptyList() {
        viewBindingScope {
            emptyListContainer.visible()
            emptyListView.playAnimation()
        }
    }

}