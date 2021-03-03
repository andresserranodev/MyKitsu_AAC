package com.puzzlebench.kitsu_aac.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.launch

class DetailViewModel constructor(private val animeRepository: AnimeRepository) : ViewModel() {

    private var _viewState = MutableLiveData<DetailsViewState>()

    val viewState: LiveData<DetailsViewState>
        get() = _viewState

    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _viewState.value = DetailsViewState.LoadInformation(animeRepository.getAnimeDetails(animeId))
        }
    }

    fun playVideo(videoId: String) {
        if (videoId.isEmpty()) {
            _viewState.value = DetailsViewState.NoVideo
        } else {
            _viewState.value = DetailsViewState.OpenVideo(videoId)
        }
    }
}