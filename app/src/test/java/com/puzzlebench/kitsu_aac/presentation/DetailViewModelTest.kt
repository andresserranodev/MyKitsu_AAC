package com.puzzlebench.kitsu_aac.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.CoroutinesTestRule
import com.puzzlebench.kitsu_aac.DummyData
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertTrue

class DetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val animeId = 1
    private val expectedVideoId = "qig4KOK2R2g"

    private val dummyAnime = DummyData.getDummyAnime("1", expectedVideoId)

    private val dummyAnimeEmptyVideo = DummyData.getDummyAnime("1", "")

    private val mockAnimeRepository = mock<AnimeRepository> {
        onBlocking { getAnimeDetails(animeId) } doReturn dummyAnime
    }

    @Test
    fun getAnimeDetails() {
        val spyLiveData = createLiveDataObserver()
        val viewModel = DetailViewModel(mockAnimeRepository)
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.getAnimeDetails(animeId)
            verify(mockAnimeRepository).getAnimeDetails(animeId)
        }
    }

    @Test
    fun `playVideo() when  youtubeVideoId is empty them viewState emmit NoVideo  state`() {
        val spyLiveData = createLiveDataObserver()
        val mockAnimeRepository = mock<AnimeRepository> {
            onBlocking { getAnimeDetails(animeId) } doReturn dummyAnimeEmptyVideo
        }
        val viewModel = DetailViewModel(mockAnimeRepository)
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.playVideo("")
            assertTrue(viewModel.viewState.value is DetailsViewState.NoVideo)
        }
    }

    @Test
    fun `playVideo() when  youtubeVideoId is not empty them viewState emmit OpenVideo  state`() {
        val spyLiveData = createLiveDataObserver()
        val viewModel = DetailViewModel(mockAnimeRepository)
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.playVideo(expectedVideoId)
            assertTrue(viewModel.viewState.value is DetailsViewState.OpenVideo)
        }
    }

    private fun createLiveDataObserver(): Observer<DetailsViewState> = spy(Observer { })
}