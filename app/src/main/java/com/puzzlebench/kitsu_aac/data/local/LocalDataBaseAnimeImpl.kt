package com.puzzlebench.kitsu_aac.data.local

import com.puzzlebench.kitsu_aac.data.local.room.AnimeDao
import com.puzzlebench.kitsu_aac.data.local.room.AnimeEntity
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeItemState

class LocalDataBaseAnimeImpl constructor(private val dao: AnimeDao) : LocalDataBaseAnime {
    override suspend fun saveAnime(anime: Anime) {
        with(anime) {
            dao.insert(
                AnimeEntity(
                    id = id,
                    description = description,
                    ageRatingGuide = ageRatingGuide,
                    ageRating = ageRating,
                    coverImageUrl = coverImageUrl,
                    posterImageUrl = posterImageUrl,
                    showType = showType,
                    status = status,
                    episodeCount = episodeCount,
                    name = name
                ) // TODO move this into mapper
            )
        }
    }

    override suspend fun getAnimeList(): List<AnimeItemState> {
        TODO("Not yet implemented")
    }
}