package com.puzzlebench.kitsu_aac.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.puzzlebench.kitsu_aac.databinding.AnimeItemBinding
import com.puzzlebench.kitsu_aac.repository.Anime

class AnimeListAdapter :
    PagingDataAdapter<Anime, RecyclerView.ViewHolder>(AnimeListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventViewHolder(
            AnimeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as EventViewHolder).bind(it)
        }
    }

    class EventViewHolder(private val biding: AnimeItemBinding) :
        RecyclerView.ViewHolder(biding.root) {
        fun bind(item: Anime) {
            biding.apply {
                anime = item
                executePendingBindings()
            }
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