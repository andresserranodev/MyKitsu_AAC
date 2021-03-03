package com.puzzlebench.kitsu_aac.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.FetchingState
import kotlinx.coroutines.launch

class AnimeListViewModel constructor(private val animeRepository: AnimeRepository) : ViewModel() {

    val allAnime = animeRepository.getAnimeState()

    private var _viewState = MutableLiveData<ListState>()

    val viewState: LiveData<ListState>
        get() = _viewState

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    init {
        initRepository()
    }

    fun listScrolled(lastVisibleItem: Int, totalItems: Int) {
        if (totalItems - 1 - lastVisibleItem == 0) {
            _viewState.value = ListState.ShowProgressBar
            if (isRequestInProgress.not()) {
                isRequestInProgress = true
                viewModelScope.launch {
                    handleResult(animeRepository.fetchAnime(totalItems))
                    isRequestInProgress = false
                }
            }

        }
    }

    fun initRepository() {
        _viewState.value = ListState.ShowProgressBar
        viewModelScope.launch {
            handleResult(animeRepository.initRepository())
        }
    }

    private fun handleResult(fetchingState: FetchingState) {
        when (fetchingState) {
            is FetchingState.Error -> {
                _viewState.value = ListState.FetchDataError(fetchingState.error.message.toString())
            }
            is FetchingState.Success ->
                _viewState.value = ListState.HideProgressBar
        }
    }
}