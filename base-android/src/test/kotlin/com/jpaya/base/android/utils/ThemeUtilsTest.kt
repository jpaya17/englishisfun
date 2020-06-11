/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.base.android.utils

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jpaya.base.android.testutils.TestCompatActivity
import com.jpaya.base.android.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ThemeUtilsTest : TestRobolectric() {

    @get:Rule
    val rule = ActivityScenarioRule(TestCompatActivity::class.java)
    private lateinit var scenario: ActivityScenario<TestCompatActivity>

    private var themeUtils = ThemeUtilsImpl()

    @Before
    fun setUp() {
        scenario = rule.scenario
    }

//    @Test
//    fun forceAppNightMode_ShouldBeDarkTheme() {
//        scenario.onActivity {
//            themeUtils.setNightMode(true)
//            it.delegate.applyDayNight()
//
//            assertTrue(themeUtils.isDarkTheme(it))
//            assertFalse(themeUtils.isLightTheme(it))
//        }
//    }

//    @Test
//    fun forceAppNoNightMode_ShouldBeLightTheme() {
//        scenario.onActivity {
//            themeUtils.setNightMode(false)
//            it.delegate.applyDayNight()
//
//            assertTrue(themeUtils.isLightTheme(it))
//            assertFalse(themeUtils.isDarkTheme(it))
//        }
//    }
}
