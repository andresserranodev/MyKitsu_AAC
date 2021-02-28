package com.puzzlebench.kitsu_aac.repository

import java.lang.Exception

sealed class AnimeItemState {
    data class Success(val data: List<Anime>) : AnimeItemState()
    data class Error(val error: Exception) : AnimeItemState()
}
