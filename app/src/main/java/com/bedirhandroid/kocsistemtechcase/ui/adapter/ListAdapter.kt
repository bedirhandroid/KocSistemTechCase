package com.bedirhandroid.kocsistemtechcase.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.kocsistemtechcase.databinding.ListRowBinding
import com.bedirhandroid.kocsistemtechcase.network.responses.DataModel
import com.bedirhandroid.kocsistemtechcase.util.loadImage

class ListAdapter(private val clickItem: (DataModel) -> Unit): PagingDataAdapter<DataModel, ListAdapter.ListAdapterVH>(LIST_COMPARATOR) {

    class ListAdapterVH(val binding: ListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            binding.apply {
                tvArtistName.text = data.artistName
                tvTrackName.text = data.trackName
                data.artworkUrl100?.let(ivPhoto::loadImage)
            }
        }
    }

    companion object {
        private val LIST_COMPARATOR =
            object : DiffUtil.ItemCallback<DataModel>() {
                override fun areItemsTheSame(
                    oldItem: DataModel,
                    newItem: DataModel
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: DataModel,
                    newItem: DataModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: ListAdapterVH, position: Int) {
        holder.apply {
            getItem(position)?.let { _data ->
                bind(_data)
                binding.btnApply.setOnClickListener { clickItem.invoke(_data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterVH {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAdapterVH(binding)
    }
}