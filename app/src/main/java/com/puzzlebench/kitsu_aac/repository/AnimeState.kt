package com.puzzlebench.kitsu_aac.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

sealed class AnimeState {
    data class Success(val data: Flow<PagingData<Anime>>) : AnimeState()
    data class Error(val error: Exception) : AnimeState()
}