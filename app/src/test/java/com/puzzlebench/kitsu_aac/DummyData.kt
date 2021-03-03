package com.puzzlebench.kitsu_aac

import com.puzzlebench.kitsu_aac.data.Attributes
import com.puzzlebench.kitsu_aac.data.ItemResponse
import com.puzzlebench.kitsu_aac.data.local.room.AnimeEntity
import com.puzzlebench.kitsu_aac.data.remote.retrofit.CoverImage
import com.puzzlebench.kitsu_aac.data.remote.retrofit.KitsuResponse
import com.puzzlebench.kitsu_aac.data.remote.retrofit.PosterImage
import com.puzzlebench.kitsu_aac.repository.Anime

object DummyData {

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
    private const val BASE_VIDEO_ID = "youtubeVideoId"

    fun getDummyKitsuResponse() = KitsuResponse(
            data = getDummyListItemResponse()
    )

    fun getDummyAnime(seed: String, youtubeVideoId: String = "$BASE_VIDEO_ID$seed") = Anime(
            id = seed.toInt(),
            name = "$BASE_CANONICAL_TITLE $seed",
            description = "$BASE_DESCRIPTION $seed",
            status = "$BASE_STATUS $seed",
            episodeCount = seed.toInt(),
            showType = "$BASE_SHOW_TYPE $seed",
            ageRating = "$BASE_AGE_RATING $seed",
            ageRatingGuide = "$BASE_AGE_RATING_GUIDE $seed",
            posterImageUrl = "$BASE_POSTER_IMAGE $seed",
            coverImageUrl = "$BASE_COVER_IMAGE $seed",
            youtubeVideoId = youtubeVideoId
    )

    fun getDummyAnimeEntity(seed: String) = AnimeEntity(
            id = seed.toInt(),
            name = "$BASE_CANONICAL_TITLE $seed",
            description = "$BASE_DESCRIPTION $seed",
            status = "$BASE_STATUS $seed",
            episodeCount = seed.toInt(),
            showType = "$BASE_SHOW_TYPE $seed",
            ageRating = "$BASE_AGE_RATING $seed",
            ageRatingGuide = "$BASE_AGE_RATING_GUIDE $seed",
            posterImageUrl = "$BASE_POSTER_IMAGE $seed",
            coverImageUrl = "$BASE_COVER_IMAGE $seed",
            youtubeVideoId = "$BASE_VIDEO_ID$seed"
    )

    private fun getDummyAttributesResponseNull(seed: String) = Attributes(
            name = "$BASE_CANONICAL_TITLE $seed",
            description = "$BASE_DESCRIPTION $seed",
            posterImage = getDummyPosterImage(seed),
            coverImage = null,
            ageRating = "$BASE_AGE_RATING $seed",
            ageRatingGuide = "$BASE_AGE_RATING_GUIDE $seed",
            episodeCount = seed.toInt(),
            status = "$BASE_STATUS $seed",
            showType = "$BASE_SHOW_TYPE $seed",
            youtubeVideoId = "$BASE_VIDEO_ID$seed"

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
            showType = "$BASE_SHOW_TYPE $seed",
            youtubeVideoId = "$BASE_VIDEO_ID$seed"
    )

    private fun getDummyItemResponse(seed: String) = ItemResponse(
            id = seed,
            attributes = getDummyAttributesResponse(seed)
    )

    fun getDummyItemResponseNullCoverImage(seed: String) = ItemResponse(
            id = seed,
            attributes = getDummyAttributesResponseNull(seed)
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

    fun getDummyListOfAnime(): List<Anime> = (1..20).map {
        getDummyAnime(it.toString())
    }

    fun getDummyListOfAnimeEntity(): List<AnimeEntity> = (1..20).map {
        getDummyAnimeEntity(it.toString())
    }
}