package com.puzzlebench.kitsu_aac.android

import android.app.Application
import com.facebook.stetho.Stetho
import com.puzzlebench.kitsu_aac.BuildConfig
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import com.puzzlebench.kitsu_aac.di.ServiceLocator

class KitsuApplication : Application() {

    val remoteFetchAnime: RemoteFetchAnime
        get() = ServiceLocator.provideRemoteFetchAnime()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}