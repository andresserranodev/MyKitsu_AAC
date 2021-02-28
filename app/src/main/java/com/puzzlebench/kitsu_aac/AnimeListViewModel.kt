package com.puzzlebench.kitsu_aac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeListViewModel constructor(animeRepository: AnimeRepository) : ViewModel() {
    val allAnime = animeRepository.getAnimeList()

    init {
        viewModelScope.launch {
            animeRepository.fetchAnimeFromServer()
        }
    }
}