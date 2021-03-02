package com.puzzlebench.kitsu_aac.data.remote

const val LIMIT_DEFAULT = 20

interface RemoteFetchAnime {
    suspend fun fetchAnime(limit: Int = LIMIT_DEFAULT, offset: Int): AnimeRemoteState
}
