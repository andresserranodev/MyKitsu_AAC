package com.puzzlebench.kitsu_aac.di

import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnimeImpl
import com.puzzlebench.kitsu_aac.data.retrofit.KitsuApi

object ServiceLocator {
    fun provideRemoteFetchAnime(): RemoteFetchAnime =
        RemoteFetchAnimeImpl(KitsuApi.makeServiceKitsuApi())
}