package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.data.local.toAnime
import com.puzzlebench.kitsu_aac.data.local.toAnimeEntity
import com.puzzlebench.kitsu_aac.DummyData.getDummyAnime
import com.puzzlebench.kitsu_aac.DummyData.getDummyAnimeEntity
import com.puzzlebench.kitsu_aac.DummyData.getDummyItemResponseNullCoverImage
import com.puzzlebench.kitsu_aac.DummyData.getDummyListItemResponse
import com.puzzlebench.kitsu_aac.data.EMPTY_STRING
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperTest {

    private val itemResponse = getDummyListItemResponse()
    private val itemResponseNullCoverImage = getDummyItemResponseNullCoverImage("1")

    private val anime = getDummyAnime("1")
    private val animeEntity = getDummyAnimeEntity("1")

    @Test
    fun transformToAnime() {
        itemResponse.map { it.transformToAnime() }
            .forEachIndexed { index, anime ->
                assertEquals(itemResponse[index].id.toInt(), anime.id)
                with(itemResponse[index].attributes) {
                    coverImage?.let {
                        assertEquals(it.coverImageUrl, anime.coverImageUrl)
                    }
                    assertEquals(name, anime.name)
                    assertEquals(description, anime.description)
                    assertEquals(ageRating, anime.ageRating)
                    assertEquals(ageRatingGuide, anime.ageRatingGuide)
                    assertEquals(episodeCount, anime.episodeCount)
                    assertEquals(showType, anime.showType)
                    assertEquals(posterImage.posterImageUrl, anime.posterImageUrl)
                    assertEquals(status, anime.status)
                }
            }
    }

    @Test
    fun transformToAnimeCoverImageNull() {
        assertEquals(EMPTY_STRING, itemResponseNullCoverImage.transformToAnime().coverImageUrl)
    }

    @Test
    fun animeTransformToAnimeEntity() {

        val result = anime.toAnimeEntity()
        with(anime) {
            assertEquals(name, result.name)
            assertEquals(description, result.description)
            assertEquals(ageRating, result.ageRating)
            assertEquals(ageRatingGuide, result.ageRatingGuide)
            assertEquals(episodeCount, result.episodeCount)
            assertEquals(showType, result.showType)
            assertEquals(posterImageUrl, result.posterImageUrl)
            assertEquals(coverImageUrl, result.coverImageUrl)
            assertEquals(status, result.status)
        }
    }

    @Test
    fun animeEntityTransformToAnime() {
        val result = animeEntity.toAnime()
        with(anime) {
            assertEquals(name, result.name)
            assertEquals(description, result.description)
            assertEquals(ageRating, result.ageRating)
            assertEquals(ageRatingGuide, result.ageRatingGuide)
            assertEquals(episodeCount, result.episodeCount)
            assertEquals(showType, result.showType)
            assertEquals(posterImageUrl, result.posterImageUrl)
            assertEquals(coverImageUrl, result.coverImageUrl)
            assertEquals(status, result.status)
        }
    }
}
