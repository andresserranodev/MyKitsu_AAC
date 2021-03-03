package com.puzzlebench.kitsu_aac.presentation

import com.puzzlebench.kitsu_aac.repository.Anime

sealed class DetailsViewState {
    class LoadInformation(val anime: Anime) : DetailsViewState()
    class OpenVideo(val videoId: String) : DetailsViewState()
    object NoVideo : DetailsViewState()
}