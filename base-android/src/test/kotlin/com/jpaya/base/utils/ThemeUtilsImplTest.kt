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

package com.jpaya.base.utils

import com.jpaya.base.extensions.isNightTime
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Auto
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Dark
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Light
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Calendar

class ThemeUtilsImplTest : TestRobolectric() {

    private lateinit var themeUtils: ThemeUtilsImpl

    @Before
    fun setUp() {
        themeUtils = ThemeUtilsImpl()
    }

    @Test
    fun `Check setAppearance works properly`() {
        assertTrue(themeUtils.isLightTheme())
        assertFalse(themeUtils.isDarkTheme())

        // Dark appearance
        themeUtils.setAppearance(Dark)
        assertFalse(themeUtils.isLightTheme())
        assertTrue(themeUtils.isDarkTheme())

        // Light appearance
        themeUtils.setAppearance(Light)
        assertTrue(themeUtils.isLightTheme())
        assertFalse(themeUtils.isDarkTheme())

        // Auto appearance
        themeUtils.setAppearance(Auto)
        if (Calendar.getInstance().isNightTime()) {
            assertFalse(themeUtils.isLightTheme())
            assertTrue(themeUtils.isDarkTheme())
        } else {
            assertTrue(themeUtils.isLightTheme())
            assertFalse(themeUtils.isDarkTheme())
        }
    }
}
