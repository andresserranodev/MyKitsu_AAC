package com.puzzlebench.kitsu_aac.repository

import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.remote.AnimeRemoteState
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl constructor(
        private val remoteFetchAnime: RemoteFetchAnime,
        private val localDataBaseAnime: LocalDataBaseAnime
) : AnimeRepository {

    companion object {
        const val ZERO_ITEM = 0
    }


    override fun getAnimeState(): AnimeState {
        return localDataBaseAnime.getAnimeList()
    }

    override suspend fun fetchAnime(offset: Int): FetchingState = withContext(Dispatchers.IO) {
        when (val remoteResponse = remoteFetchAnime.fetchAnime(offset = offset)) {
            is AnimeRemoteState.Success ->
                remoteResponse.data.forEach {
                    localDataBaseAnime.saveAnime(it)
                }
            is AnimeRemoteState.Error -> {
                return@withContext FetchingState.Error(remoteResponse.error)
            }
        }
        return@withContext FetchingState.Success
    }

    override suspend fun initRepository(): FetchingState = withContext(Dispatchers.IO) {
        if (localDataBaseAnime.getAnimeCount() == ZERO_ITEM) {
            return@withContext fetchAnime(ZERO_ITEM)
        }
        return@withContext FetchingState.Success
    }


    override suspend fun getAnimeDetails(animeId: Int): Anime = withContext(Dispatchers.IO) {
        return@withContext localDataBaseAnime.getAnimeById(animeId)
    }
}
