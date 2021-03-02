package com.puzzlebench.kitsu_aac.presentation.android

import android.app.Application
import com.facebook.stetho.Stetho
import com.puzzlebench.kitsu_aac.BuildConfig
import com.puzzlebench.kitsu_aac.di.ServiceLocator
import com.puzzlebench.kitsu_aac.repository.AnimeRepository

class KitsuApplication : Application() {

    val animeRepository: AnimeRepository
        get() = ServiceLocator.provideAnimeRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}