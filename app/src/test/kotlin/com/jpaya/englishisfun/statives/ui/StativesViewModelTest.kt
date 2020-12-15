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

package com.jpaya.englishisfun.statives.ui

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.jpaya.englishisfun.statives.ui.StativesViewState.ListReady
import com.jpaya.englishisfun.statives.ui.StativesViewState.Loading
import com.jpaya.englishisfun.statives.ui.StativesViewState.NetworkError
import com.jpaya.englishisfun.statives.ui.model.StativeItem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class StativesViewModelTest : ViewModelTest() {

    companion object {
        private val MOCK_ITEMS = listOf(
            StativeItem(
                id = 1,
                category = "Category 1",
                verbs = mutableListOf("Verb 1")
            ),
            StativeItem(
                id = 2,
                category = "Category 2",
                verbs = mutableListOf("Verb 2")
            )
        )
    }

    private lateinit var presenter: StativesPresenter

    @Before
    fun setUp() {
        presenter = mock()
    }

    @Test
    fun `Stative items are loaded properly from presenter upon creation`() = runBlockingTest {
        whenever(presenter.getStativeItems()).doReturn(MOCK_ITEMS)

        val vm = StativesViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                ListReady(MOCK_ITEMS)
            )
        }
    }

    @Test
    fun `Presenter error leads to error state upon creation`() = runBlockingTest {
        whenever(presenter.getStativeItems()).thenAnswer {
            throw IOException("No internet available")
        }

        val vm = StativesViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                NetworkError
            )
        }
    }

    @Test
    fun `Reload after error can load items properly`() = runBlockingTest {
        var invocations = 0
        whenever(presenter.getStativeItems()).thenAnswer {
            when (invocations++) {
                0 -> throw IOException("Network error")
                else -> MOCK_ITEMS
            }
        }

        val vm = StativesViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.reload()

            stateObserver.assertObserved(
                NetworkError,
                Loading,
                ListReady(MOCK_ITEMS)
            )
        }
    }
}
