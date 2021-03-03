package com.puzzlebench.kitsu_aac.presentation

sealed class ListState {
    object HideProgressBar : ListState()
    object ShowProgressBar : ListState()
    class FetchDataError(val error: String) : ListState()
}