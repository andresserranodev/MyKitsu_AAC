package com.puzzlebench.kitsu_aac.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.puzzlebench.kitsu_aac.data.local.room.AnimeDao
import com.puzzlebench.kitsu_aac.data.local.room.toAnime
import com.puzzlebench.kitsu_aac.data.local.room.toAnimeEntity
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.flow.map
import java.lang.Exception

const val PAGE_SIZE = 15

class LocalDataBaseAnimeImpl constructor(private val dao: AnimeDao) : LocalDataBaseAnime {

    override suspend fun saveAnime(anime: Anime) {
        dao.insert(anime.toAnimeEntity())
    }

    override fun getAnimeList(): AnimeState {
        return try {
            AnimeState.Success(
                Pager(PagingConfig(
                        pageSize = PAGE_SIZE,
                        enablePlaceholders = true,
                        maxSize = 200)) {
                    dao.getAnimeList()
                }.flow.map { pagingData -> pagingData.map { it.toAnime() } })
        } catch (ex: Exception) {
            AnimeState.Error(ex)
        }
    }
}