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

package com.jpaya.dynamicfeatures.abbreviations.ui.adapter

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AbbreviationsListAdapterStateTest {

    lateinit var state: AbbreviationsListAdapterState

    @Test
    fun setStateAsAdded_ShouldBeSettled() {
        state = AbbreviationsListAdapterState.Added

        assertTrue(state.isAdded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = AbbreviationsListAdapterState.AddLoading

        assertFalse(state.isAdded())
        assertTrue(state.isAddLoading())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = AbbreviationsListAdapterState.AddError

        assertFalse(state.isAdded())
        assertFalse(state.isAddLoading())
        assertTrue(state.isAddError())
        assertFalse(state.isNoMore())
    }

    @Test
    fun setStateAsNoMore_ShouldBeSettled() {
        state = AbbreviationsListAdapterState.NoMore

        assertFalse(state.isAdded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isAddError())
        assertTrue(state.isNoMore())
    }
}
