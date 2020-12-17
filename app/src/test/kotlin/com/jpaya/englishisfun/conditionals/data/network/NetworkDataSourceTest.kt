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

package com.jpaya.englishisfun.conditionals.data.network

import com.jpaya.englishisfun.conditionals.data.network.model.ConditionalNetworkItem
import com.jpaya.englishisfun.conditionals.data.network.model.ConditionalsResponse
import com.jpaya.englishisfun.conditionals.domain.Conditional
import com.jpaya.englishisfun.data.firebase.FireStoreClient
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
        private val MOCK_CONDITIONALS_DOCUMENT = ConditionalsResponse().apply {
            conditionals = mutableListOf(
                ConditionalNetworkItem().apply {
                    id = 1
                    name = "Name 1"
                    condition = "Condition 1"
                    result = "Result 1"
                    uses = mutableListOf("Use 1")
                    examples = mutableListOf("Example 1")
                },
                ConditionalNetworkItem().apply {
                    id = 2
                    name = "Name 2"
                    condition = "Condition 2"
                    result = "Result 2"
                    uses = mutableListOf("Use 1")
                    examples = mutableListOf("Example 1")
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
        whenever(fireStoreClient.conditionals()).doReturn(success(MOCK_CONDITIONALS_DOCUMENT))
        val expectedResult = listOf(
            Conditional(
                id = 1,
                name = "Name 1",
                condition = "Condition 1",
                result = "Result 1",
                uses = mutableListOf("Use 1"),
                examples = mutableListOf("Example 1")
            ),
            Conditional(
                id = 2,
                name = "Name 2",
                condition = "Condition 2",
                result = "Result 2",
                uses = mutableListOf("Use 1"),
                examples = mutableListOf("Example 1")
            )
        )
        assertEquals(expectedResult, dataSource.getConditionalsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getConditionalsItems works properly when null list`() = runBlockingTest {
        whenever(fireStoreClient.conditionals()).doReturn(success(ConditionalsResponse()))
        assertEquals(emptyList<Conditional>(), dataSource.getConditionalsItems())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Check getConditionalsItems works properly when null`() = runBlockingTest {
        whenever(fireStoreClient.conditionals()).doReturn(failure(Exception()))
        assertEquals(emptyList<Conditional>(), dataSource.getConditionalsItems())
    }
}
