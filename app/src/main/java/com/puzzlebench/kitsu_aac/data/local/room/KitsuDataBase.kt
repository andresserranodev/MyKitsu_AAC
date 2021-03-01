package com.puzzlebench.kitsu_aac.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

const val DB_NAME = "KitsuDataBase.db"
@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
abstract class KitsuDataBase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}