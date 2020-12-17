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

package com.jpaya.englishisfun.statives.data.network

import com.jpaya.englishisfun.data.firebase.FireStoreClient
import com.jpaya.englishisfun.statives.data.network.model.StativeNetworkItem
import com.jpaya.englishisfun.statives.data.network.model.StativesResponse
import com.jpaya.englishisfun.statives.domain.Stative
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
        private val MOCK_STATIVES_DOCUMENT = StativesResponse().apply {
            statives = mutableListOf(
                StativeNetworkItem().apply {
                    id = 1
                    category = "Category 1"
                    verbs = mutableListOf("Verb 1")
                },
                StativeNetworkItem().apply {
                    id = 2
                    category = "Category 2"
                    verbs = mutableListOf("Verb 2")
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
    fun `Check getConditionalsItems works properly`() = runBlockingTest {
        whenever(fireStoreClient.statives()).doReturn(success(MOCK_STATIVES_DOCUMENT))
        val expectedResult = listOf(
            Stative(
                id = 1,
                category = "Category 1",
                verbs = mutableListOf("Verb 1")
            ),
            Stative(
                id = 2,
                category = "Category 2",
                verbs = mutableListOf("Verb 2")
            )
        )
        assertEquals(expectedResult, dataSource.getStativeItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getConditionalsItems works properly when null list`() = runBlockingTest {
        whenever(fireStoreClient.statives()).doReturn(success(StativesResponse()))
        assertEquals(emptyList<Stative>(), dataSource.getStativeItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getConditionalsItems works properly when null`() = runBlockingTest {
        whenever(fireStoreClient.statives()).doReturn(failure(Exception()))
        assertEquals(emptyList<Stative>(), dataSource.getStativeItems())
    }
}
