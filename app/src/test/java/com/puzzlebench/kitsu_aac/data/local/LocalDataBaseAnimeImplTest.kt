package com.puzzlebench.kitsu_aac.data.local

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.data.local.room.AnimeDao
import com.puzzlebench.kitsu_aac.DummyData.getDummyAnime
import com.puzzlebench.kitsu_aac.DummyData.getDummyAnimeEntity
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalDataBaseAnimeImplTest {

    private lateinit var localDataBaseAnime: LocalDataBaseAnime

    private val expectedCount = 0
    private val animeId = 1
    private val animeEntity = getDummyAnimeEntity("1")
    private val animeDao = mock<AnimeDao> {
        onBlocking { getCount() } doReturn expectedCount
        onBlocking { getAnimeById(animeId) } doReturn animeEntity
    }

    private val errorExpected = "dummyApiError"

    private val mockErrorIOException = mock<IOException> {
        onBlocking { message } doReturn errorExpected
    }
    private val mockErrorAnimeDao = mock<AnimeDao> {
        onBlocking { getPagingSource() } doAnswer {
            throw mockErrorIOException
        }
    }

    @Before
    fun setUp() {
        localDataBaseAnime = LocalDataBaseAnimeImpl(animeDao)
    }

    @Test
    fun testSaveAnime() {
        runBlocking {
            val dummyAnimeList = getDummyAnime("1")
            localDataBaseAnime.saveAnime(dummyAnimeList)
            verify(animeDao).insert(dummyAnimeList.toAnimeEntity())
        }
    }

    @Test
    fun getAnimeCount() {
        runBlocking {
            val result = localDataBaseAnime.getAnimeCount()
            verify(animeDao).getCount()
            assertEquals(expectedCount, result)
        }
    }

    @Test
    fun getAnimeList() {
        runBlocking {
            val result = localDataBaseAnime.getAnimeList()
            assertTrue(result is AnimeState.Success)
        }
    }

    @Test
    fun getAnimeListError() {
        val localDataBaseAnimeError = LocalDataBaseAnimeImpl(mockErrorAnimeDao)
        runBlocking {
            val result = localDataBaseAnimeError.getAnimeList()
            assertTrue(result is AnimeState.Success)
        }
    }

    @Test
    fun getAnimeById() {
        runBlocking {
            localDataBaseAnime.getAnimeById(animeId)
            verify(animeDao).getAnimeById(animeId)
        }
    }
}