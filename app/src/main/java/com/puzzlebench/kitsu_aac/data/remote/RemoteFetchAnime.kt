package com.puzzlebench.kitsu_aac.data.remote

interface RemoteFetchAnime {
    suspend fun fetchAnime(limit: Int, offset: Int): AnimeRemoteState
}
