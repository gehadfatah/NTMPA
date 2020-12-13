package com.goda.npmoa

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.goda.npmoa.presentation_layer.common.espresso_idling.Espressoo

import com.goda.npmoa.presentation_layer.ui.home.HomeActivity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class NewsTst {


    @Rule
    @JvmField
    var activityRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(HomeActivity::class.java, false, true)

    @Before
    fun setup() = runBlocking {
        IdlingRegistry.getInstance().register(Espressoo.getIdlingResource())
        return@runBlocking
    }

    @Test
    fun onItemRecyclerViewItemClicked() {
        NewsListScreen.clickOnFirstRecyclerItem()
        Thread.sleep(2000)
    }

    @After
    fun onTestFinish() {
        IdlingRegistry.getInstance().unregister(Espressoo.getIdlingResource())
    }


}
