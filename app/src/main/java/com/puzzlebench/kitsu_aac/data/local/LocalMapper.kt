package com.puzzlebench.kitsu_aac.data.local

import com.puzzlebench.kitsu_aac.data.local.room.AnimeEntity
import com.puzzlebench.kitsu_aac.repository.Anime

fun Anime.toAnimeEntity(): AnimeEntity {
    return with(this) {
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
        )
    }
}

fun AnimeEntity.toAnime(): Anime {
    return with(this) {
        Anime(
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
        )
    }
}