package com.puzzlebench.kitsu_aac.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val ageRating: String,
    val ageRatingGuide: String,
    val posterImageUrl: String,
    val coverImageUrl: String,
    val episodeCount: Int,
    val status: String,
    val showType: String,
    val youtubeVideoId: String
)
