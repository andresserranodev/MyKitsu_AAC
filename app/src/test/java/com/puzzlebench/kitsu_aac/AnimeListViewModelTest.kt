package com.puzzlebench.kitsu_aac

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

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

    private val mockAnimeRepository = mock<AnimeRepository> {
        onBlocking { getAnimeState() } doReturn AnimeState.Success(expectedFlow)
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
    fun listScrolled() {
        val lasItemVisible = 19
        val totalOfItem = 20
        val viewModel = AnimeListViewModel(mockAnimeRepository)
        runBlocking {
            viewModel.listScrolled(lasItemVisible, totalOfItem)
            verify(mockAnimeRepository).fetchAnime(totalOfItem)
        }
    }
}