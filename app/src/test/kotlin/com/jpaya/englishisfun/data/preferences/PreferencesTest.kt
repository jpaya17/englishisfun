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

import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.junit.Assert.assertEquals
import org.junit.Test

class PreferencesTest : TestRobolectric() {

    @Test
    fun `Check getAppearance and putAppearance work properly`() {
        assertEquals("Auto", Preferences.getAppearance(context)) // Default value
        val appearance = "Light"
        Preferences.putAppearance(appearance, context)
        assertEquals(appearance, Preferences.getAppearance(context))
    }
}
