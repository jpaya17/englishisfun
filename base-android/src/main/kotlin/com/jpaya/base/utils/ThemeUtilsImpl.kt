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

import androidx.appcompat.app.AppCompatDelegate
import com.jpaya.base.extensions.isNightTime
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Auto
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Dark
import com.jpaya.base.utils.ThemeUtilsImpl.Appearance.Light
import java.util.Calendar
import javax.inject.Inject

/**
 * Utils implementation for application theme configuration.
 * @see ThemeUtils
 */
class ThemeUtilsImpl @Inject constructor() : ThemeUtils {

    enum class Appearance {
        Auto,
        Dark,
        Light
    }

    /**
     * Whether the current configuration is a dark theme i.e. in Night configuration.
     */
    override fun isDarkTheme() = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

    /**
     * Whether the current configuration is a light theme i.e. in Day configuration.
     */
    override fun isLightTheme() = !isDarkTheme()

    /**
     * Force [AppCompatDelegate] Mode to night/day.
     *
     * @param forceNight Boolean that force night mode otherwise notnight is configured.
     */
    private fun setNightMode(forceNight: Boolean) = AppCompatDelegate.setDefaultNightMode(
        if (forceNight) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
    )

    override fun setAppearance(appearance: Appearance) {
        when (appearance) {
            Auto -> setNightMode(Calendar.getInstance().isNightTime())
            Dark -> setNightMode(true)
            Light -> setNightMode(false)
        }
    }
}
