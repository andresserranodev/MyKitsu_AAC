package com.puzzlebench.kitsu_aac.data.local

import androidx.paging.PagingSource
import com.puzzlebench.kitsu_aac.data.local.room.AnimeEntity
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeState

interface LocalDataBaseAnime {
    suspend fun saveAnime(anime: Anime)
    fun getAnimeListPaging(): PagingSource<Int, AnimeEntity>
    fun getAnimeList(): AnimeState
    suspend fun getAnimeCount(): Int
}