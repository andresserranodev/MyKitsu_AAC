package com.puzzlebench.kitsu_aac.repository

data class Anime(
    val id: Int,
    val name: String,
    val description: String,
    val ageRating: String,
    val ageRatingGuide: String,
    val posterImageUrl: String,
    val coverImageUrl: String,
    val episodeCount: Int,
    val status: String,
    val showType: String
)