package com.puzzlebench.kitsu_aac.repository

import androidx.paging.PagingData
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.DummyData.getDummyAnime
import com.puzzlebench.kitsu_aac.DummyData.getDummyListOfAnime
import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.remote.AnimeRemoteState
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AnimeRepositoryImplTest {

    private val expectedCount = 0
    private val expectedOffset = 20
    private val animeId = 1

    private val dummyListOfAnime = getDummyListOfAnime()
    private val dummyAnime = getDummyAnime("1")

    private lateinit var animeRepository: AnimeRepository

    private val errorExpected = "dummyApiError"

    private val mockErrorIOException = mock<IOException> {
        onBlocking { message } doReturn errorExpected
    }

    private val mockRemoteFetchAnime = mock<RemoteFetchAnime> {
        onBlocking { fetchAnime(offset = expectedOffset) } doReturn AnimeRemoteState.Success(
            dummyListOfAnime
        )
    }

    private val expectedFlow = flow {
        emit(PagingData.from(dummyListOfAnime))
        delay(10)
    }

    private val mockLocalDataBaseAnime = mock<LocalDataBaseAnime> {
        onBlocking { getAnimeCount() } doReturn expectedCount
        onBlocking { getAnimeList() } doReturn AnimeState.Success(expectedFlow)
        onBlocking { getAnimeById(animeId) } doReturn dummyAnime
    }

    @Before
    fun setUp() {
        animeRepository = AnimeRepositoryImpl(mockRemoteFetchAnime, mockLocalDataBaseAnime)
    }

    @Test
    fun getAnimeState() {
        runBlocking {
            animeRepository.fetchAnime(expectedOffset)
            verify(mockRemoteFetchAnime).fetchAnime(offset = expectedOffset)
            dummyListOfAnime.forEach {
                verify(mockLocalDataBaseAnime).saveAnime(it)
            }
        }
    }

    @Test
    fun fetchAnimeSuccessState() {
        runBlocking {
            val result = animeRepository.getAnimeState()
            verify(mockLocalDataBaseAnime).getAnimeList()
            assertTrue(result is AnimeState.Success)
            assertEquals(expectedFlow, result.data)
        }
    }

    @Test
    fun fetchAnimeSuccessError() {
        runBlocking {
            val mockLocalDataBaseAnimeError = mock<LocalDataBaseAnime> {
                onBlocking { getAnimeList() } doReturn AnimeState.Error(mockErrorIOException)
            }

            val animeRepository =
                AnimeRepositoryImpl(mockRemoteFetchAnime, mockLocalDataBaseAnimeError)
            val result = animeRepository.getAnimeState()
            assertTrue(result is AnimeState.Error)
            assertEquals(errorExpected, result.error.message)
        }
    }

    @Test
    fun initRepository() {
        runBlocking {
            animeRepository.initRepository()
            verify(mockLocalDataBaseAnime).getAnimeCount()
            verify(mockRemoteFetchAnime).fetchAnime(offset = ZERO_ITEM)
        }
    }

    @Test
    fun getAnimeDetails() {
        runBlocking {
            animeRepository.getAnimeDetails(animeId)
            verify(mockLocalDataBaseAnime).getAnimeById(animeId)
        }
    }
}