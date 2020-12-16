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

package com.jpaya.englishisfun.abbreviations.mapper

import com.jpaya.englishisfun.abbreviations.data.db.AbbreviationRoomItem
import com.jpaya.englishisfun.abbreviations.data.network.model.AbbreviationNetworkItem
import com.jpaya.englishisfun.abbreviations.domain.Abbreviation
import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import org.junit.Assert.assertEquals
import org.junit.Test

class AbbreviationMapperTest {

    @Test
    fun `Check domain to room works properly`() {
        val domain = Abbreviation(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        val expectedResult = AbbreviationRoomItem(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        assertEquals(expectedResult, domain.toRoomItem())
    }

    @Test
    fun `Check room to domain works properly`() {
        val room = AbbreviationRoomItem(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        val expectedResult = Abbreviation(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        assertEquals(expectedResult, room.toDomain())
    }

    @Test
    fun `Check network to domain works properly`() {
        val network = AbbreviationNetworkItem().apply {
            id = 1
            abbr = "Abbreviation"
            desc = "Description"
        }

        val expectedResult = Abbreviation(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        assertEquals(expectedResult, network.toDomain())
    }

    @Test
    fun `Check domain to presentation works properly`() {
        val domain = Abbreviation(
            id = 1,
            abbr = "Abbreviation",
            desc = "Description"
        )

        val expectedResult = SimpleListItem2(
            id = 1,
            text1 = "Abbreviation",
            text2 = "Description"
        )

        assertEquals(expectedResult, domain.toPresentation())
    }
}
