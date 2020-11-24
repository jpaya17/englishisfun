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

package com.jpaya.englishisfun.abbreviations.ui.adapter

import android.widget.FrameLayout
import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import com.jpaya.englishisfun.databinding.SimpleListItem2Binding
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AbbreviationsAdapterTest : TestRobolectric() {

    private val itemsList = listOf(
        SimpleListItem2(
            id = 1,
            text1 = "Abbreviation",
            text2 = "Description"
        ),
        SimpleListItem2(
            id = 2,
            text1 = "Another Abbreviation",
            text2 = "Another Description"
        ),
    )
    private lateinit var adapter: AbbreviationsAdapter

    @Before
    fun setUp() {
        adapter = AbbreviationsAdapter()
    }

    @Test
    fun `Check itemCount works properly`() {
        assertEquals(0, adapter.itemCount)
        adapter.setData(itemsList)
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `Check onCreateViewHolder and onBindViewHolder works properly`() {
        adapter.setData(itemsList)

        val viewHolder = adapter.onCreateViewHolder(FrameLayout(context), 0)
        val binding = viewHolder.binding

        assertNotNull(viewHolder)
        assertThat(binding, instanceOf(SimpleListItem2Binding::class.java))

        adapter.onBindViewHolder(viewHolder, 1)
        assertEquals("Another Abbreviation", binding.tvText1.text.toString())
        assertEquals("Another Description", binding.tvText2.text.toString())
    }
}
