package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.repository.AnimeItemState

interface RemoteFetchAnime {
    suspend fun fetchAnime(limit: Int, offset: Int): AnimeItemState
}
