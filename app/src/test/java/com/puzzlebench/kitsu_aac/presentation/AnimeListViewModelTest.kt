package com.puzzlebench.kitsu_aac.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.CoroutinesTestRule
import com.puzzlebench.kitsu_aac.DummyData
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.AnimeState
import com.puzzlebench.kitsu_aac.repository.FetchingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.io.IOException
import kotlin.test.assertTrue

class AnimeListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule: CoroutinesTestRule = CoroutinesTestRule()

    private val dummyListOfAnime = DummyData.getDummyListOfAnime()

    private val expectedFlow = flow {
        emit(PagingData.from(dummyListOfAnime))
        delay(10)
    }

    private val totalOfItem = 20

    private val errorExpected = "dummyApiError"

    private val mockErrorIOException = mock<IOException> {
        onBlocking { message } doReturn errorExpected
    }

    private val mockAnimeRepository = mock<AnimeRepository> {
        onBlocking { getAnimeState() } doReturn AnimeState.Success(expectedFlow)
        onBlocking { initRepository() } doReturn FetchingState.Success
        onBlocking { fetchAnime(totalOfItem) } doReturn FetchingState.Success
    }

    private val mockAnimeRepositoryError = mock<AnimeRepository> {
        onBlocking { getAnimeState() } doReturn AnimeState.Success(expectedFlow)
        onBlocking { initRepository() } doReturn FetchingState.Error(mockErrorIOException)
        onBlocking { fetchAnime(totalOfItem) } doReturn FetchingState.Error(mockErrorIOException)
    }

    @Test
    fun init() {
        AnimeListViewModel(mockAnimeRepository)
        runBlocking {
            verify(mockAnimeRepository).initRepository()
        }
    }

    @Test
    fun getAllAnime() {
        val viewModel = AnimeListViewModel(mockAnimeRepository)
        runBlocking {
            viewModel.allAnime
            verify(mockAnimeRepository).getAnimeState()
        }
    }

    @Test
    fun `initRepository() when repository is ok  them viewState emit  ListState HideProgressBar`() {
        val viewModel = AnimeListViewModel(mockAnimeRepository)
        val spyLiveData = createLiveDataObserver()
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            verify(mockAnimeRepository).initRepository()
            assertTrue(viewModel.viewState.value is ListState.HideProgressBar)
        }
    }

    @Test
    fun `initRepository() when repository has errors them viewState emit  ListState FetchDataError`() {
        val viewModel = AnimeListViewModel(mockAnimeRepositoryError)
        val spyLiveData = createLiveDataObserver()
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            verify(mockAnimeRepositoryError).initRepository()
            assertTrue(viewModel.viewState.value is ListState.FetchDataError)
        }
    }

    @Test
    fun `listScrolled() when repository is ok  them viewState emit  ListState HideProgressBar`() {
        val lasItemVisible = 19
        val viewModel = AnimeListViewModel(mockAnimeRepository)
        val spyLiveData = createLiveDataObserver()
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.listScrolled(lasItemVisible, totalOfItem)
            verify(mockAnimeRepository).fetchAnime(totalOfItem)
            assertTrue(viewModel.viewState.value is ListState.HideProgressBar)
        }
    }

    @Test
    fun `listScrolled() when repository has errors them viewState emit  ListState FetchDataError`() {
        val lasItemVisible = 19
        val viewModel = AnimeListViewModel(mockAnimeRepositoryError)
        val spyLiveData = createLiveDataObserver()
        viewModel.viewState.observeForever(spyLiveData)
        runBlocking {
            viewModel.listScrolled(lasItemVisible, totalOfItem)
            assertTrue(viewModel.viewState.value is ListState.FetchDataError)
            verify(mockAnimeRepositoryError).fetchAnime(totalOfItem)
        }
    }

    private fun createLiveDataObserver(): Observer<ListState> = spy(Observer { })
}