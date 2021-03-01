package com.puzzlebench.kitsu_aac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeListViewModel constructor(private val animeRepository: AnimeRepository) : ViewModel() {

    val allAnime = animeRepository.getAnimeState()

    init {
        viewModelScope.launch {
            animeRepository.initRepository()
        }
    }

    fun listScrolled(lastVisibleItem: Int, totalItems: Int) {
        if (totalItems - 1 - lastVisibleItem == 0) {
            viewModelScope.launch {
                animeRepository.fetchAnime(totalItems)
            }
        } // TODO show and hide Loader
    }
}