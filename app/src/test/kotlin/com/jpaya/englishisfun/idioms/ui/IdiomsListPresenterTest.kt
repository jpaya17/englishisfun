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

import com.jpaya.englishisfun.base.ui.model.SimpleListItem2
import com.jpaya.englishisfun.idioms.domain.Idiom
import com.jpaya.englishisfun.idioms.domain.IdiomsInteractor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class IdiomsListPresenterTest {

    companion object {
        private val MOCK_IDIOMS_LIST = listOf(
            Idiom(
                id = 1,
                idiom = "Idiom 1",
                description = "Description 1"
            ),
            Idiom(
                id = 2,
                idiom = "Idiom 2",
                description = "Description 2"
            )
        )
    }

    private lateinit var interactor: IdiomsInteractor
    private lateinit var presenter: IdiomsListPresenter

    @Before
    fun setUp() {
        interactor = mock()
        presenter = IdiomsListPresenter(interactor)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getIdiomsItems works properly`() = runBlocking {
        whenever(interactor.getIdiomsItems()).doReturn(MOCK_IDIOMS_LIST)

        val expectedResult = listOf(
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

        assertEquals(expectedResult, presenter.getIdiomsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check searchIdioms works properly`() = runBlocking {
        val filter = "Idiom"
        whenever(interactor.searchIdioms(filter)).doReturn(MOCK_IDIOMS_LIST)

        val expectedResult = listOf(
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

        assertEquals(expectedResult, presenter.searchIdioms(filter))
    }
}
