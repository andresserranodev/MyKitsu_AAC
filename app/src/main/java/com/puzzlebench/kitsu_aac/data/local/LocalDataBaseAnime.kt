package com.puzzlebench.kitsu_aac.data.local

import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeState

interface LocalDataBaseAnime {
    suspend fun saveAnime(anime: Anime)
    fun getAnimeList(): AnimeState
    suspend fun getAnimeCount(): Int
}