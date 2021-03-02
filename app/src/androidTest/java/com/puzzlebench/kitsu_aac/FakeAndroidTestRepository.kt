package com.puzzlebench.kitsu_aac

import androidx.paging.PagingData
import com.puzzlebench.kitsu_aac.repository.Anime
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import com.puzzlebench.kitsu_aac.repository.AnimeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class FakeAndroidTestRepository : AnimeRepository {

    companion object {
        const val BASE_CANONICAL_TITLE = "canonical title"

        const val BASE_DESCRIPTION = "any description"
        const val BASE_POSTER_IMAGE =
            "https://media.kitsu.io/anime/poster_images/4/small.jpg?1597698321"
        private const val BASE_COVER_IMAGE =
            "https://media.kitsu.io/anime/poster_images/4/small.jpg?1597698321"

        const val BASE_AGE_RATING = "T"
        const val BASE_AGE_RATING_GUIDE = "Teens 13 or older"
        const val BASE_STATUS = "current"
        const val BASE_SHOW_TYPE = "movie"
    }

    private val dummyListOfAnime = getDummyListOfAnime()

    private val expectedFlow = flow {
        emit(PagingData.from(dummyListOfAnime))
        delay(10)
    }

    override fun getAnimeState(): AnimeState = AnimeState.Success(expectedFlow)

    override suspend fun fetchAnime(offset: Int) {
        println("fetchAnime")
    }

    override suspend fun initRepository() {
        println("initRepository")
    }

    private fun getDummyListOfAnime(): List<Anime> = (1..20).map {
        getDummyAnime(it.toString())
    }

    private fun getDummyAnime(seed: String) = Anime(
        id = seed.toInt(),
        name = "$BASE_CANONICAL_TITLE $seed",
        description = "$BASE_DESCRIPTION $seed",
        status = "$BASE_STATUS $seed",
        episodeCount = seed.toInt(),
        showType = "$BASE_SHOW_TYPE $seed",
        ageRating = "$BASE_AGE_RATING $seed",
        ageRatingGuide = "$BASE_AGE_RATING_GUIDE $seed",
        posterImageUrl = "$BASE_POSTER_IMAGE $seed",
        coverImageUrl = "$BASE_COVER_IMAGE $seed"
    )
}