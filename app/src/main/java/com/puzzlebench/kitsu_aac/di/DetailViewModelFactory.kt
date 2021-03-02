package com.puzzlebench.kitsu_aac.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.puzzlebench.kitsu_aac.presentation.DetailViewModel
import com.puzzlebench.kitsu_aac.repository.AnimeRepository

class DetailViewModelFactory constructor(private val animeRepository: AnimeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(animeRepository) as T
    }
}