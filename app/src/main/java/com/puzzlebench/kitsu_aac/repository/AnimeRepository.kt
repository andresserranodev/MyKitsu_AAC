package com.puzzlebench.kitsu_aac.repository

interface AnimeRepository {
    fun getAnimeState(): AnimeState
    suspend fun fetchAnime(offset: Int): FetchingState
    suspend fun initRepository(): FetchingState
    suspend fun getAnimeDetails(animeId: Int): Anime
}