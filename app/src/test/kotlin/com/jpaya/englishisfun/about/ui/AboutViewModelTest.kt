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

package com.jpaya.englishisfun.about.ui

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModelTest : ViewModelTest() {

    companion object {
        private const val MOCK_VERSION = "1.0 (1)"
    }

    @Test
    fun `Version is loaded correctly from presenter upon creation`() = runBlockingTest {
        val presenter: AboutPresenter = mock()
        whenever(presenter.getVersion()).doReturn(MOCK_VERSION)

        val vm = AboutViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                AboutViewState.Loaded(MOCK_VERSION)
            )
        }
    }
}
