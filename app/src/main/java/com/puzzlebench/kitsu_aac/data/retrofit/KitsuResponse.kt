package com.puzzlebench.kitsu_aac.data.retrofit

import com.google.gson.annotations.SerializedName
import com.puzzlebench.kitsu_aac.data.ItemResponse

class KitsuResponse(@SerializedName("data") val data: List<ItemResponse> = emptyList())
class PosterImage(@SerializedName("small") val posterImageUrl: String = "")
class CoverImage(@SerializedName("small") val coverImageUrl: String? = "")
