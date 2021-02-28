package com.puzzlebench.kitsu_aac.repository

interface AnimeRepository {
    suspend fun getAnimeList(): AnimeItemState
}