package com.puzzlebench.kitsu_aac.data.remote

import com.puzzlebench.kitsu_aac.data.remote.DummyData.getDummyListItemResponse
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperTest {

    private val itemResponse = getDummyListItemResponse()

    @Test
    fun transformToAnime() {
        itemResponse.map { it.transformToAnime() }
            .forEachIndexed { index, anime ->
                assertEquals(itemResponse[index].id.toInt(), anime.id)
                with(itemResponse[index].attributes) {
                    assertEquals(name, anime.name)
                    assertEquals(description, anime.description)
                    assertEquals(ageRating, anime.ageRating)
                    assertEquals(ageRatingGuide, anime.ageRatingGuide)
                    assertEquals(episodeCount, anime.episodeCount)
                    assertEquals(showType, anime.showType)
                    assertEquals(posterImage.posterImageUrl, anime.posterImageUrl)
                    assertEquals(coverImage!!.coverImageUrl, anime.coverImageUrl)
                    assertEquals(status, anime.status)
                }
            }
    }
}
