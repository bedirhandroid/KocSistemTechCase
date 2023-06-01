package com.bedirhandroid.kocsistemtechcase.ui.fragments.detail

import com.bedirhandroid.kocsistemtechcase.R
import com.bedirhandroid.kocsistemtechcase.base.BaseFragment
import com.bedirhandroid.kocsistemtechcase.databinding.FragmentDetailBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.Constant
import com.bedirhandroid.kocsistemtechcase.util.customGetSerializable
import com.bedirhandroid.kocsistemtechcase.util.loadImage

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() {
    private var detailDataModel: DataModel? = null
    override fun initView() {
        getArgs()
        viewBindingScope {
            detailDataModel?.let {
                it.artworkUrl100?.let(ivArtist::loadImage)
                txtArtistName.text = it.artistName
                txtCollectionName.text = getString(R.string.collection_name, it.collectionName)
                txtDesc.text = it.shortDescription
                txtTrackName.text = getString(R.string.track_name, it.trackName)
                txtTrackPrice.text = getString(R.string.track_price, "${it.trackPrice} ${it.currency}")
                txtCollectionPrice.text = getString(R.string.collection_price, "${it.collectionPrice} ${it.currency}")
            }
        }
    }

    override fun initListeners() {}

    override fun initObservers() {}

    private fun getArgs() {
        detailDataModel = arguments?.customGetSerializable(Constant.KEY_DETAIL_DATA)
    }

}