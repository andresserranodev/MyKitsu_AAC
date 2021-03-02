package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.data.remote.retrofit.KitsuApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteFetchAnimeImpl constructor(
    private val api: KitsuApi
) : RemoteFetchAnime {
    override suspend fun fetchAnime(limit: Int, offset: Int): AnimeRemoteState =
        withContext(Dispatchers.IO) {
            return@withContext try {
                AnimeRemoteState.Success(
                    api.getAnime(limit, offset).data.map {
                        it.transformToAnime()
                    }
                )
            } catch (ex: Exception) {
                AnimeRemoteState.Error(error = ex)
            }
        }
}