package com.goda.npmoa

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.agoda.kakao.Screen


object NewsListScreen : Screen<NewsListScreen>() {

    fun clickOnFirstRecyclerItem() {
        onView(
            RecyclerViewMatcher(R.id.resultsBeanRecyclerView).atPositionOnView(0, R.id.container)
        ).perform(click())
    }
}