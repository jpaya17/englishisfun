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

package com.jpaya.base.ui.extensions

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpaya.libraries.testutils.TestFragment
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Test

class FragmentExtensionsTest : TestRobolectric() {

    @Test
    fun providedViewModelByNothing_ShouldObtainWithStateSaved() {
        val scenario = launchFragmentInContainer<TestFragment>()
        scenario.onFragment {
            val expectedState = Lifecycle.State.INITIALIZED
            val createdViewModel = it.viewModel {
                TestViewModel(expectedState)
            }
            assertThat(createdViewModel, instanceOf(TestViewModel::class.java))
            assertEquals(expectedState, createdViewModel.state)
        }
    }

    @Test
    fun providedViewModelByIdentifier_ShouldObtainWithStateSaved() {
        val scenario = launchFragmentInContainer<TestFragment>()
        scenario.onFragment {
            val identifier = "TestViewModel"
            val expectedState = Lifecycle.State.INITIALIZED
            val createdViewModel = it.viewModel(identifier) {
                TestViewModel(expectedState)
            }
            assertThat(createdViewModel, instanceOf(TestViewModel::class.java))
            assertEquals(expectedState, createdViewModel.state)
        }
    }

    @Test
    fun providedViewModelByFactory_ShouldObtainWithStateSaved() {
        val scenario = launchFragmentInContainer<TestFragment>()
        scenario.onFragment {
            val expectedState = Lifecycle.State.INITIALIZED
            val createdViewModel = it.viewModel {
                MyViewModelFactory(expectedState).create(TestViewModel::class.java)
            }
            assertThat(createdViewModel, instanceOf(TestViewModel::class.java))
            assertEquals(expectedState, createdViewModel.state)
        }
    }

    private class TestViewModel(val state: Lifecycle.State? = null) : ViewModel()

    private class MyViewModelFactory(val state: Lifecycle.State?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Lifecycle.State::class.java).newInstance(state)
        }
    }
}
