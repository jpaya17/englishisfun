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

package com.jpaya.englishisfun.idioms.ui.model

import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import org.junit.Assert.*
import org.junit.Test

class IdiomItemTest {

    @Test
    fun `Init should initialise properly`() {
        val id: Long = 1
        val idiom = "Idiom"
        val description = "Description"

        val item = SimpleListItem2(id, idiom, description)

        assertEquals(id, item.id)
        assertEquals(idiom, item.text1)
        assertEquals(description, item.text2)
    }

    @Test
    fun `Check that comparator works properly`() {
        // Different data
        val item1 = SimpleListItem2(1, "Idiom 1", "Description 1")
        val item2 = SimpleListItem2(2, "Idiom 2", "Description 2")
        assertFalse(item1.isSameItemAs(item2))
        assertFalse(item1.hasSameContentsAs(item2))

        // Same Id
        val item3 = SimpleListItem2(1, "Idiom 3", "Description 3")
        assertTrue(item1.isSameItemAs(item3))
        assertFalse(item1.hasSameContentsAs(item3))

        // Same data
        val item4 = SimpleListItem2(1, "Idiom 1", "Description 1")
        assertTrue(item1.isSameItemAs(item4))
        assertTrue(item1.hasSameContentsAs(item4))
    }
}
