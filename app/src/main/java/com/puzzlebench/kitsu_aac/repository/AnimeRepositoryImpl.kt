package com.puzzlebench.kitsu_aac.repository

import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.remote.AnimeRemoteState
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime

class AnimeRepositoryImpl constructor(
    private val remoteFetchAnime: RemoteFetchAnime,
    private val localDataBaseAnime: LocalDataBaseAnime
) : AnimeRepository {
    override suspend fun getAnimeList(): AnimeState {
        return localDataBaseAnime.getAnimeList()
    }

    override suspend fun fetchAnimeFromServer() {
        for (i in 20 until 101 step 20) {
            when (val remoteResponse = remoteFetchAnime.fetchAnime(20, i - 20)) {
                is AnimeRemoteState.Success ->
                    remoteResponse.data.forEach {
                        localDataBaseAnime.saveAnime(it)
                    }
                else -> {
                    println("error")
                }
            }
        }
    }
}
