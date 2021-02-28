package com.puzzlebench.kitsu_aac.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity): Long

    @Query("SELECT * FROM ANIME_TABLE")
    fun getAnimeList(): PagingSource<Int, AnimeEntity>
}