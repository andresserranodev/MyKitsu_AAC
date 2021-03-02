package com.puzzlebench.kitsu_aac.presentation.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.puzzlebench.kitsu_aac.databinding.AnimeItemBinding
import com.puzzlebench.kitsu_aac.repository.Anime

typealias Listener = (Anime) -> Unit

class AnimeListAdapter(private val listener: Listener) :
    PagingDataAdapter<Anime, RecyclerView.ViewHolder>(AnimeListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AnimeViewHolder(
            AnimeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as AnimeViewHolder).bind(it)
        }
    }

    class AnimeViewHolder(private val biding: AnimeItemBinding, private val listener: Listener) :
        RecyclerView.ViewHolder(biding.root) {
        fun bind(item: Anime) {
            biding.apply {
                anime = item
                executePendingBindings()
            }.setClickListener { listener(item) }
        }
    }
}

private class AnimeListDiffCallback : DiffUtil.ItemCallback<Anime>() {

    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem == newItem
    }
}