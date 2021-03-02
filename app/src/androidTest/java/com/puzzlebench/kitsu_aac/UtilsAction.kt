package com.puzzlebench.kitsu_aac

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class UtilsAction {
    companion object {
        fun performClickById(chileId: Int): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String = ""

                override fun getConstraints(): Matcher<View> {
                    return CoreMatchers.allOf(ViewMatchers.isAssignableFrom(ViewGroup::class.java))
                }

                override fun perform(uiController: UiController?, view: View?) {
                    val v = view?.findViewById<ViewGroup>(chileId)
                    v?.performClick()
                }
            }
        }
    }
}
