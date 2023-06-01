package com.bedirhandroid.kocsistemtechcase.ui.fragments.biglist

import DynamicLayoutAdapter
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseFragment
import com.bedirhandroid.kocsistemtechcase.databinding.FragmentBigListBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.ui.activities.search.SearchActivity
import com.bedirhandroid.kocsistemtechcase.util.LocalDataManager
import com.bedirhandroid.kocsistemtechcase.util.showAlert

class BigListFragment : BaseFragment<FragmentBigListBinding, BigListViewModel>() {

    private lateinit var horizontalBigListAdapter: DynamicLayoutAdapter
    override fun initView() {
        viewBindingScope {
            viewModel.localData?.let {
                horizontalBigListAdapter = DynamicLayoutAdapter(
                    it,
                    3,
                    ::onClickItem,
                    ::onDeleteItem
                )
                val horizontalLayoutManager =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                rvHorizontal.apply {
                    layoutManager = horizontalLayoutManager
                    adapter = horizontalBigListAdapter
                }
            }
        }
    }

    override fun initListeners() {

    }

    override fun initObservers() {

    }

    private fun onClickItem(data: DataModel) {

    }

    private fun onDeleteItem(data: DataModel) {
        requireContext().showAlert(getString(R.string.delete_song), getString(R.string.attention)) {
            viewModel.localData?.remove(data)
            LocalDataManager.getInstance().localListData = viewModel.localData
            horizontalBigListAdapter.notifyDataSetChanged()
            if (viewModel.localData.isNullOrEmpty()) {
                requireActivity().apply {
                    Intent(this, SearchActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                }
            }
        }
    }

}