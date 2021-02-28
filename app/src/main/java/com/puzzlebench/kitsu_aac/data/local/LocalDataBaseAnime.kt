package com.puzzlebench.kitsu_aac.data.local

import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeItemState

interface LocalDataBaseAnime {
    suspend fun saveAnime(anime: Anime)
    suspend fun getAnimeList(): List<AnimeItemState> // TODO replace by flow
}