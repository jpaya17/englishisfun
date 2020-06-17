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

package com.jpaya.dynamicfeatures.abbreviations.ui.model

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AbbreviationItemMapperTest {

    private lateinit var mapper: AbbreviationItemMapper

    @Before
    fun setUp() {
        mapper = AbbreviationItemMapper()
    }

    @Test
    fun map_withEmptyList_shouldReturnEmptyList() = runBlocking {
        val list = mutableListOf<HashMap<String, String>>()
        assertTrue(mapper.map(list).isEmpty())
    }

    @Test
    fun map_withNonEmptyList_shouldReturnNonEmptyList() = runBlocking {
        val list = mutableListOf(
            hashMapOf("abbr" to "Abbr", "desc" to "Desc"),
            hashMapOf("abbr" to "Abbr_2", "desc" to "Desc_2")
        )
        val expectedResult = mutableListOf(
            AbbreviationItem(1, "Abbr", "Desc"),
            AbbreviationItem(2, "Abbr_2", "Desc_2")
        )
        assertEquals(mapper.map(list), expectedResult)
    }

    @Test
    fun map_withIncompleteMaps_shouldIgnoreIncompleteMaps() = runBlocking {
        var list = mutableListOf(
            hashMapOf("abbr" to "Abbr"),
            hashMapOf("abbr" to "Abbr_2", "desc" to "Desc_2")
        )
        var expectedResult = mutableListOf(
            AbbreviationItem(1, "Abbr_2", "Desc_2")
        )
        assertEquals(mapper.map(list), expectedResult)

        list = mutableListOf(
            hashMapOf("desc" to "Desc"),
            hashMapOf("abbr" to "Abbr_2", "desc" to "Desc_2")
        )
        expectedResult = mutableListOf(
            AbbreviationItem(1, "Abbr_2", "Desc_2")
        )
        assertEquals(mapper.map(list), expectedResult)

        list = mutableListOf(
            hashMapOf(),
            hashMapOf("abbr" to "Abbr", "desc" to "Desc")
        )
        expectedResult = mutableListOf(
            AbbreviationItem(1, "Abbr", "Desc")
        )
        assertEquals(mapper.map(list), expectedResult)
    }
}
