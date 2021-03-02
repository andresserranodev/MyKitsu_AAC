package com.puzzlebench.kitsu_aac.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.CoroutinesTestRule
import com.puzzlebench.kitsu_aac.DummyData
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val animeId = 1
    private val dummyAnime = DummyData.getDummyAnime("1")

    private val mockAnimeRepository = mock<AnimeRepository> {
        onBlocking { getAnimeDetails(animeId) } doReturn dummyAnime
    }

    @Test
    fun getAnimeDetails() {
        val spyLiveData = createLiveDataObserver()
        val viewModel = DetailViewModel(mockAnimeRepository)
        viewModel.data.observeForever(spyLiveData)
        runBlocking {
            viewModel.getAnimeDetails(animeId)
            verify(mockAnimeRepository).getAnimeDetails(animeId)
        }
    }

    private fun createLiveDataObserver(): Observer<Anime> = spy(Observer { })
}