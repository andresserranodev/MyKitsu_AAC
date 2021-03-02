package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.repository.Anime
import java.lang.Exception

sealed class AnimeRemoteState {
    data class Success(val data: List<Anime>) : AnimeRemoteState()
    data class Error(val error: Exception) : AnimeRemoteState()
}
