package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.data.remote.retrofit.KitsuApi
import com.puzzlebench.kitsu_aac.repository.AnimeItemState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteFetchAnimeImpl constructor(
    private val api: KitsuApi
) : RemoteFetchAnime {
    override suspend fun fetchAnime(limit: Int, offset: Int): AnimeItemState =
        withContext(Dispatchers.IO) {
            return@withContext try {
                AnimeItemState.Success(
                    api.getAnime(limit, offset).data.map {
                        it.transformToAnime()
                    }
                )
            } catch (ex: Exception) {
                AnimeItemState.Error(error = ex)
            }
        }
}