package com.puzzlebench.kitsu_aac.repository

interface AnimeRepository {
    suspend fun getAnimeList(): AnimeState
    suspend fun fetchAnimeFromServer()
}