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

package com.jpaya.englishisfun.conditionals.ui

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.jpaya.englishisfun.conditionals.ui.ConditionalsListViewState.ListReady
import com.jpaya.englishisfun.conditionals.ui.ConditionalsListViewState.Loading
import com.jpaya.englishisfun.conditionals.ui.ConditionalsListViewState.NetworkError
import com.jpaya.englishisfun.conditionals.ui.model.ConditionalItem
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ConditionalsListViewModelTest : ViewModelTest() {

    companion object {
        private val MOCK_ITEMS = listOf(
            ConditionalItem(
                id = 1,
                name = "Name 1",
                condition = "Condition 1",
                result = "Result 1",
                uses = "Use 1",
                examples = "Example 1"
            ),
            ConditionalItem(
                id = 2,
                name = "Name 2",
                condition = "Condition 2",
                result = "Result 2",
                uses = "Use 1",
                examples = "Example 1"
            )
        )
    }

    private lateinit var presenter: ConditionalsListPresenter

    @Before
    fun setUp() {
        presenter = mock()
    }

    @Test
    fun `Irregular items are loaded properly from presenter upon creation`() = runBlockingTest {
        whenever(presenter.getConditionalsItems()).doReturn(MOCK_ITEMS)

        val vm = ConditionalsListViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                ListReady(MOCK_ITEMS)
            )
        }
    }

    @Test
    fun `Presenter error leads to error state upon creation`() = runBlockingTest {
        whenever(presenter.getConditionalsItems()).thenAnswer {
            throw IOException("No internet available")
        }

        val vm = ConditionalsListViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                NetworkError
            )
        }
    }

    @Test
    fun `Reload after error can load items properly`() = runBlockingTest {
        var invocations = 0
        whenever(presenter.getConditionalsItems()).thenAnswer {
            when (invocations++) {
                0 -> throw IOException("Network error")
                else -> MOCK_ITEMS
            }
        }

        val vm = ConditionalsListViewModel(presenter)

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
