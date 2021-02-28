package com.puzzlebench.kitsu_aac.repository

import com.puzzlebench.kitsu_aac.data.local.LocalDataBaseAnime
import com.puzzlebench.kitsu_aac.data.remote.RemoteFetchAnime

class AnimeRepositoryImpl constructor(
    private val remoteFetchAnime: RemoteFetchAnime,
    private val localDataBaseAnime: LocalDataBaseAnime
) : AnimeRepository {
    override suspend fun getAnimeList(): AnimeItemState {
        val remoteResponse = remoteFetchAnime.fetchAnime(20, 0)
        when (remoteResponse) {
            is AnimeItemState.Success ->
                remoteResponse.data.forEach {
                    localDataBaseAnime.saveAnime(it)
                }
            else -> {
                println("error")
            }
        }
        return remoteResponse
    }
}