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
    fun getPagingSource(): PagingSource<Int, AnimeEntity>

    @Query("SELECT COUNT(*) FROM ANIME_TABLE")
    suspend fun getCount(): Int

    @Query("SELECT * from ANIME_TABLE WHERE id = :animeId")
    suspend fun getAnimeById(animeId: Int): AnimeEntity
}