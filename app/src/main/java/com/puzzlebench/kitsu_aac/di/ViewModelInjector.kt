package com.puzzlebench.kitsu_aac.di

import com.puzzlebench.kitsu_aac.repository.AnimeRepository

object ViewModelInjector {

    fun provideAnimeListViewModelFactory(animeRepository: AnimeRepository) =
        AnimeListViewModelFactory(
            animeRepository
        )
}
