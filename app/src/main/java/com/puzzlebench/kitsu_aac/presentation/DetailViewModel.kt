package com.puzzlebench.kitsu_aac.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val animeRepository: AnimeRepository) : ViewModel() {

    private var _data = MutableLiveData<Anime>()

    val data: LiveData<Anime>
        get() = _data

    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _data.value = animeRepository.getAnimeDetails(animeId)
        }
    }
}