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

package com.jpaya.englishisfun.phrasals.data.network

import com.jpaya.englishisfun.data.firebase.FireStoreClient
import com.jpaya.englishisfun.phrasals.data.network.model.PhrasalNetworkItem
import com.jpaya.englishisfun.phrasals.data.network.model.PhrasalsResponse
import com.jpaya.englishisfun.phrasals.domain.Phrasal
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
        private val MOCK_PHRASALS_DOCUMENT = PhrasalsResponse().apply {
            phrasals = listOf(
                PhrasalNetworkItem().apply {
                    id = 1
                    verb = "Verb 1"
                    definitions = "Definitions 1"
                },
                PhrasalNetworkItem().apply {
                    id = 2
                    verb = "Verb 2"
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
    fun `Check getPhrasalsItems works properly`() = runBlockingTest {
        whenever(fireStoreClient.phrasals()).doReturn(success(MOCK_PHRASALS_DOCUMENT))
        val expectedResult = listOf(
            Phrasal(
                id = 1,
                verb = "Verb 1",
                definitions = "Definitions 1"
            ),
            Phrasal(
                id = 2,
                verb = "Verb 2",
                definitions = "Definitions 2"
            )
        )
        assertEquals(expectedResult, dataSource.getPhrasalsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getPhrasalsItems works properly when null list`() = runBlockingTest {
        whenever(fireStoreClient.phrasals()).doReturn(success(PhrasalsResponse()))
        assertEquals(emptyList<Phrasal>(), dataSource.getPhrasalsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getPhrasalsItems works properly when null`() = runBlockingTest {
        whenever(fireStoreClient.phrasals()).doReturn(failure(Exception()))
        assertEquals(emptyList<Phrasal>(), dataSource.getPhrasalsItems())
    }
}
