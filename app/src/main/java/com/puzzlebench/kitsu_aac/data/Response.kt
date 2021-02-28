package com.puzzlebench.kitsu_aac.data

import com.google.gson.annotations.SerializedName
import com.puzzlebench.kitsu_aac.data.remote.retrofit.CoverImage
import com.puzzlebench.kitsu_aac.data.remote.retrofit.PosterImage
import com.puzzlebench.kitsu_aac.repository.Anime

const val EMPTY_STRING = ""

class ItemResponse(
    val id: String,
    val attributes: Attributes
) {
    fun transformToAnime(): Anime {
        with(attributes) {
            val coverUrl = if (coverImage == null) EMPTY_STRING else {
                coverImage.coverImageUrl ?: EMPTY_STRING
            }
            return Anime(
                id.toInt(),
                name,
                description,
                ageRating,
                ageRatingGuide,
                posterImage.posterImageUrl,
                coverUrl,
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
    val coverImage: CoverImage?,
    val episodeCount: Int,
    val status: String,
    val showType: String
)