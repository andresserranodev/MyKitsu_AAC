package com.puzzlebench.kitsu_aac.di

import android.content.Context
import androidx.room.Room
import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnimeImpl
import com.puzzlebench.kitsu_aac.data.local.room.KitsuDataBase
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnimeImpl
import com.puzzlebench.kitsu_aac.data.remote.retrofit.KitsuApi
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.AnimeRepositoryImpl

object ServiceLocator {

    private var database: KitsuDataBase? = null

    fun provideAnimeRepository(context: Context): AnimeRepository =
        AnimeRepositoryImpl(
            provideRemoteFetchAnime(),
            provideLocalDataBaseAnime(context)
        )

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
            KitsuDataBase::class.java, "KitsuDataBase.db"
        ).build()
        database = result
        return result
    }
}