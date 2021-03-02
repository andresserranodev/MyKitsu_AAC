package com.puzzlebench.kitsu_aac.android

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.puzzlebench.kitsu_aac.FakeAndroidTestRepository
import com.puzzlebench.kitsu_aac.FakeAndroidTestRepository.Companion.BASE_CANONICAL_TITLE
import com.puzzlebench.kitsu_aac.R
import com.puzzlebench.kitsu_aac.di.ServiceLocator
import com.puzzlebench.kitsu_aac.repository.AnimeRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ListFragmentTest  {
    private lateinit var animeRepository: AnimeRepository

    @Test
    fun whenItemClickedThemNavigateToDetailsFragment() {
        animeRepository = FakeAndroidTestRepository()
        ServiceLocator.animeRepository = animeRepository

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        // Create a graphical Fragment
        val listBusinessesFragment = launchFragmentInContainer<ListFragment>(null, R.style.Theme_Kitsu_AAC)
        // Set the NavController property on the fragment
        listBusinessesFragment.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        val itemPosition = 1
        Espresso.onView(withId(R.id.items_list))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("$BASE_CANONICAL_TITLE $itemPosition")),
                    ViewActions.click()
                ))

        assertEquals(navController.currentDestination?.label, "details_fragment")
    }
}