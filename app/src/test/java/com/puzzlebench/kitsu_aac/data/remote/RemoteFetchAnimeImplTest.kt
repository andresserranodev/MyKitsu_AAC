package com.puzzlebench.kitsu_aac.data.remote

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.puzzlebench.kitsu_aac.data.remote.DummyData.getDummyKitsuResponse
import com.puzzlebench.kitsu_aac.data.retrofit.KitsuApi
import com.puzzlebench.kitsu_aac.repository.AnimeItemState
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RemoteFetchAnimeImplTest {

    private val limit = 20
    private val offset = 0

    private val dummyServiceResponse = getDummyKitsuResponse()

    private val errorExpected = "dummyApiError"

    private val mockErrorIOException = mock<IOException> {
        onBlocking { message } doReturn errorExpected
    }
    private val mockKitsuApi = mock<KitsuApi> {
        onBlocking { getAnime(limit, offset) } doReturn dummyServiceResponse
    }
    private val mockErrorKitsuApi = mock<KitsuApi> {
        onBlocking { getAnime(limit, offset) } doAnswer {
            throw mockErrorIOException
        }
    }

    @Test
    fun testFetchAnime() {
        val remoteFetchAnime = RemoteFetchAnimeImpl(mockKitsuApi)
        runBlocking {
            val result = remoteFetchAnime.fetchAnime(limit, offset)
            verify(mockKitsuApi).getAnime(limit, offset)
            assertTrue(result is AnimeItemState.Success)
            result.data.forEachIndexed { index, anime ->
                assertEquals(dummyServiceResponse.data[index].id.toInt(), anime.id)
            }
        }
    }

    @Test
    fun testFetchAnimeError() {
        val remoteFetchAnime = RemoteFetchAnimeImpl(mockErrorKitsuApi)
        runBlocking {
            val result = remoteFetchAnime.fetchAnime(limit, offset)
            verify(mockErrorKitsuApi).getAnime(limit, offset)
            assertTrue(result is AnimeItemState.Error)
            assertEquals(errorExpected, result.error.message)
        }
    }
}