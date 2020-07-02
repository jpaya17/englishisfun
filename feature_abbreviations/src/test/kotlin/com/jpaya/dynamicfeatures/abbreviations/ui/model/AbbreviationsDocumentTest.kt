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

import org.junit.Assert.assertEquals
import org.junit.Test

class AbbreviationsDocumentTest {

    @Test
    fun abbreviationDocument_shouldInitialiseProperly() {
        val document = AbbreviationsDocument()
        val item = AbbreviationItem().apply {
            id = 1
            abbr = "LOL"
            desc = "Laugh out loud"
        }
        document.abbreviations = listOf(item)

        assertEquals(1, document.abbreviations.size)
        assertEquals(1, document.abbreviations[0].id)
        assertEquals("LOL", document.abbreviations[0].abbr)
        assertEquals("Laugh out loud", document.abbreviations[0].desc)
    }
}
