package com.puzzlebench.kitsu_aac.data

import com.google.gson.annotations.SerializedName
import com.puzzlebench.kitsu_aac.data.retrofit.CoverImage
import com.puzzlebench.kitsu_aac.data.retrofit.PosterImage
import com.puzzlebench.kitsu_aac.repository.Anime

class ItemResponse(
    val id: String,
    val attributes: Attributes
) {
    fun transformToAnime(): Anime {
        with(attributes) {
            return Anime(
                id.toInt(),
                name,
                description,
                ageRating,
                ageRatingGuide,
                posterImage.posterImageUrl,
                coverImage.coverImageUrl,
                episodeCount,
                status,
                showType
            )
        }
    }
}

class Attributes(
    @SerializedName("canonicalTitle") val name: String,
    val description: String,
    val ageRating: String,
    val ageRatingGuide: String,
    val posterImage: PosterImage,
    @SerializedName("coverImage ") val coverImage: CoverImage,
    val episodeCount: Int,
    val status: String,
    val showType: String
)