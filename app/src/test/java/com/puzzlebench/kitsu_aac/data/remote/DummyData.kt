package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.data.Attributes
import com.puzzlebench.kitsu_aac.data.ItemResponse
import com.puzzlebench.kitsu_aac.data.retrofit.CoverImage
import com.puzzlebench.kitsu_aac.data.retrofit.KitsuResponse
import com.puzzlebench.kitsu_aac.data.retrofit.PosterImage

object DummyData {

    private const val ID = "1"
    private const val BASE_CANONICAL_TITLE = "canonical title"

    private const val BASE_DESCRIPTION = "any description"
    private const val BASE_POSTER_IMAGE =
        "https://media.kitsu.io/anime/poster_images/4/small.jpg?1597698321"
    private const val BASE_COVER_IMAGE =
        "https://media.kitsu.io/anime/poster_images/4/small.jpg?1597698321"

    private const val BASE_AGE_RATING = "T"
    private const val BASE_AGE_RATING_GUIDE = "Teens 13 or older"
    private const val BASE_STATUS = "current"
    private const val BASE_SHOW_TYPE = "Movie"

    fun getDummyKitsuResponse() = KitsuResponse(
        data = getDummyListItemResponse()
    )

    private fun getDummyAttributesResponse(seed: String) = Attributes(
        name = "$BASE_CANONICAL_TITLE $seed",
        description = "$BASE_DESCRIPTION $seed",
        posterImage = getDummyPosterImage(seed),
        coverImage = getDummyCoverImage(seed),
        ageRating = "$BASE_AGE_RATING $seed",
        ageRatingGuide = "$BASE_AGE_RATING_GUIDE $seed",
        episodeCount = seed.toInt(),
        status = "$BASE_STATUS $seed",
        showType = "$BASE_SHOW_TYPE $seed"

    )

    private fun getDummyItemResponse(seed: String) = ItemResponse(
        id = seed,
        attributes = getDummyAttributesResponse(seed)
    )

    private fun getDummyPosterImage(seed: String) = PosterImage(
        "$BASE_POSTER_IMAGE $seed"
    )

    private fun getDummyCoverImage(seed: String) = CoverImage(
        "$BASE_COVER_IMAGE $seed"
    )

    fun getDummyListItemResponse(): List<ItemResponse> = (1..20).map {
        getDummyItemResponse(it.toString())
    }
}