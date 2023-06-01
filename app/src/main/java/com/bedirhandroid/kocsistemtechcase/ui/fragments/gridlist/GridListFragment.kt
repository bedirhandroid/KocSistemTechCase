package com.bedirhandroid.kocsistemtechcase.ui.fragments.gridlist

import DynamicLayoutAdapter
import android.os.Bundle
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseFragment
import com.bedirhandroid.kocsistemtechcase.databinding.FragmentGridListBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.Constant
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import com.bedirhandroid.kocsistemtechcase.util.navigateWithBundleTo
import com.bedirhandroid.kocsistemtechcase.util.visible


class GridListFragment : BaseFragment<FragmentGridListBinding, GridListViewModel>() {
    private lateinit var gridListAdapter: DynamicLayoutAdapter
    override fun initView() {
        LocalDataManager.getInstance().localListData?.let {
            viewBindingScope {
                gridListAdapter = DynamicLayoutAdapter(
                    it,
                    2,
                    ::onClickItem
                )
                rvGrid.adapter = gridListAdapter
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
            navigateWithBundleTo(R.id.action_navigation_grid_list_to_detailFragment, it)
        }
    }

    private fun showEmptyList() {
        viewBindingScope {
            emptyListContainer.visible()
            emptyListView.playAnimation()
        }
    }

}