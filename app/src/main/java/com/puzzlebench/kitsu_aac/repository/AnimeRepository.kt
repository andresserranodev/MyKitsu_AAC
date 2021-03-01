package com.puzzlebench.kitsu_aac.repository

interface AnimeRepository {
    fun getAnimeState(): AnimeState
    suspend fun fetchAnime(offset: Int)
    suspend fun initRepository()
}