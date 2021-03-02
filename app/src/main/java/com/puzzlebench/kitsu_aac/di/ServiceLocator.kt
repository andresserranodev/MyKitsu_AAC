package com.puzzlebench.kitsu_aac.di

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnimeImpl
import com.puzzlebench.kitsu_aac.data.local.room.DB_NAME
import com.puzzlebench.kitsu_aac.data.local.room.KitsuDataBase
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnimeImpl
import com.puzzlebench.kitsu_aac.data.remote.retrofit.KitsuApi
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.AnimeRepositoryImpl

object ServiceLocator {

    private var database: KitsuDataBase? = null

    @Volatile
    var animeRepository: AnimeRepository? = null
        @VisibleForTesting set

    fun provideAnimeRepository(context: Context): AnimeRepository {
        synchronized(this) {
            return animeRepository ?: AnimeRepositoryImpl(
                provideRemoteFetchAnime(),
                provideLocalDataBaseAnime(context)
            )
        }
    }


    private fun provideRemoteFetchAnime(): RemoteFetchAnime =
        RemoteFetchAnimeImpl(KitsuApi.makeServiceKitsuApi())

    private fun provideLocalDataBaseAnime(context: Context): LocalDataBaseAnime {
        val database = database
            ?: createDataBase(context)
        return LocalDataBaseAnimeImpl(database.animeDao())
    }

    private fun createDataBase(context: Context): KitsuDataBase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            KitsuDataBase::class.java, DB_NAME
        ).build()
        database = result
        return result
    }
}