package com.puzzlebench.kitsu_aac.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzlebench.kitsu_aac.AnimeListViewModel
import com.puzzlebench.kitsu_aac.repository.AnimeRepository

@Suppress("UNCHECKED_CAST")
class AnimeListViewModelFactory constructor(private val animeRepository: AnimeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnimeListViewModel(animeRepository) as T
    }
}