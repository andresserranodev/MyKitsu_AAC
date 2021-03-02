package com.puzzlebench.kitsu_aac.repository

import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.remote.AnimeRemoteState
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val ZERO_ITEM = 0

class AnimeRepositoryImpl constructor(
    private val remoteFetchAnime: RemoteFetchAnime,
    private val localDataBaseAnime: LocalDataBaseAnime
) : AnimeRepository {

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun getAnimeState(): AnimeState {
        return localDataBaseAnime.getAnimeList()
    }

    override suspend fun fetchAnime(offset: Int) = withContext(Dispatchers.IO) {
        if (isRequestInProgress.not()) {
            val remoteResponse = remoteFetchAnime.fetchAnime(offset = offset)
            isRequestInProgress = true
            when (remoteResponse) {
                is AnimeRemoteState.Success ->
                    remoteResponse.data.forEach {
                        localDataBaseAnime.saveAnime(it)
                    }
                else -> {
                    println("error")
                }
            }
            isRequestInProgress = false
        }
    }

    override suspend fun initRepository() {
        if (localDataBaseAnime.getAnimeCount() == ZERO_ITEM) {
            fetchAnime(ZERO_ITEM)
        }
    }

    override suspend fun getAnimeDetails(animeId: Int): Anime = withContext(Dispatchers.IO) {
        return@withContext localDataBaseAnime.getAnimeById(animeId)
    }
}
