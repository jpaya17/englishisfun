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

package com.jpaya.englishisfun.data.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE

object Preferences {

    private const val FILE_NAME = "secret_preferences"

    // Pref Keys
    private const val APPEARANCE_PREF_KEY = "appearance"

    // Default Values
    private const val DEFAULT_APPERANCE = "Auto"

    fun putAppearance(appearance: String, applicationContext: Context) =
        getPreferences(applicationContext).edit().putString(APPEARANCE_PREF_KEY, appearance).apply()

    fun getAppearance(applicationContext: Context) =
        getPreferences(applicationContext).getString(APPEARANCE_PREF_KEY, DEFAULT_APPERANCE)!!

    private fun getPreferences(applicationContext: Context) =
        applicationContext.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
}
