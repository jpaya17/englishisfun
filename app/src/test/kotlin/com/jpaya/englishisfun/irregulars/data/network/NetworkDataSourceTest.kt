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

package com.jpaya.englishisfun.irregulars.data.network

import com.jpaya.englishisfun.data.firebase.FireStoreClient
import com.jpaya.englishisfun.irregulars.data.network.model.IrregularNetworkItem
import com.jpaya.englishisfun.irregulars.data.network.model.IrregularsResponse
import com.jpaya.englishisfun.irregulars.domain.Irregular
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class NetworkDataSourceTest {

    companion object {
        private val MOCK_IRREGULARS_DOCUMENT = IrregularsResponse().apply {
            irregulars = listOf(
                IrregularNetworkItem().apply {
                    id = 1
                    base = "Base 1"
                    simple = "Simple 1"
                    participle = "Participle 1"
                    definitions = "Definitions 1"
                },
                IrregularNetworkItem().apply {
                    id = 2
                    base = "Base 2"
                    simple = "Simple 2"
                    participle = "Participle 2"
                    definitions = "Definitions 2"
                }
            )
        }
    }

    private lateinit var fireStoreClient: FireStoreClient
    private lateinit var dataSource: NetworkDataSource

    @Before
    fun setUp() {
        fireStoreClient = mock()
        dataSource = NetworkDataSource(fireStoreClient)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getIrregularsItems works properly`() = runBlockingTest {
        whenever(fireStoreClient.irregulars()).doReturn(success(MOCK_IRREGULARS_DOCUMENT))
        val expectedResult = listOf(
            Irregular(
                id = 1,
                base = "Base 1",
                simple = "Simple 1",
                participle = "Participle 1",
                definitions = "Definitions 1"
            ),
            Irregular(
                id = 2,
                base = "Base 2",
                simple = "Simple 2",
                participle = "Participle 2",
                definitions = "Definitions 2"
            )
        )
        assertEquals(expectedResult, dataSource.getIrregularsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getIrregularsItems works properly when null list`() = runBlockingTest {
        whenever(fireStoreClient.irregulars()).doReturn(success(IrregularsResponse()))
        assertEquals(emptyList<Irregular>(), dataSource.getIrregularsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getIrregularsItems works properly when null`() = runBlockingTest {
        whenever(fireStoreClient.irregulars()).doReturn(failure(Exception()))
        assertEquals(emptyList<Irregular>(), dataSource.getIrregularsItems())
    }
}
