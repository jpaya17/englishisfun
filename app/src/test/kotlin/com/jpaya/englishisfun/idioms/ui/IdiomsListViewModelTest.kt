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

package com.jpaya.englishisfun.idioms.ui

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import com.jpaya.englishisfun.idioms.ui.IdiomsListViewState.ListReady
import com.jpaya.englishisfun.idioms.ui.IdiomsListViewState.Loading
import com.jpaya.englishisfun.idioms.ui.IdiomsListViewState.NetworkError
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class IdiomsListViewModelTest : ViewModelTest() {

    companion object {
        private val MOCK_ITEMS = listOf(
            SimpleListItem2(
                id = 1,
                text1 = "Idiom 1",
                text2 = "Description 1"
            ),
            SimpleListItem2(
                id = 2,
                text1 = "Idiom 2",
                text2 = "Description 2"
            )
        )

        private val MOCK_ITEMS_FILTERED = listOf(
            SimpleListItem2(
                id = 1,
                text1 = "Idiom 1",
                text2 = "Description 1"
            )
        )
    }

    private lateinit var presenter: IdiomsListPresenter

    @Before
    fun setUp() {
        presenter = mock()
    }

    @Test
    fun `Idiom items are loaded properly from presenter upon creation`() = runBlockingTest {
        whenever(presenter.getIdiomsItems()).doReturn(MOCK_ITEMS)

        val vm = IdiomsListViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                ListReady(MOCK_ITEMS)
            )
        }
    }

    @Test
    fun `Presenter error leads to error state upon creation`() = runBlockingTest {
        whenever(presenter.getIdiomsItems()).thenAnswer {
            throw IOException("No internet available")
        }

        val vm = IdiomsListViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                NetworkError
            )
        }
    }

    @Test
    fun `Reload after error can load items properly`() = runBlockingTest {
        var invocations = 0
        whenever(presenter.getIdiomsItems()).thenAnswer {
            when (invocations++) {
                0 -> throw IOException("Network error")
                else -> MOCK_ITEMS
            }
        }

        val vm = IdiomsListViewModel(presenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.reload()

            stateObserver.assertObserved(
                NetworkError,
                Loading,
                ListReady(MOCK_ITEMS)
            )
        }
    }

    @Test
    fun `Search feature loads items properly`() = runBlockingTest {
        var invocations = 0
        val filter = "Base 1"
        whenever(presenter.searchIdioms(filter)).thenAnswer {
            when (invocations++) {
                0 -> throw IOException("Network error")
                else -> MOCK_ITEMS_FILTERED
            }
        }

        val vm = IdiomsListViewModel(presenter)

        vm.search(filter)
        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                NetworkError
            )
        }

        vm.search(filter)
        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                ListReady(MOCK_ITEMS_FILTERED)
            )
        }

        vm.resetSearch()
        vm.observeStateAndEvents { stateObserver, _ ->
            stateObserver.assertObserved(
                Loading
            )
        }
    }
}
