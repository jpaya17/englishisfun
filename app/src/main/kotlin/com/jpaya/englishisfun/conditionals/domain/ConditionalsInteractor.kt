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

package com.jpaya.englishisfun.conditionals.domain

import com.jpaya.englishisfun.conditionals.data.db.DatabaseDataSource
import com.jpaya.englishisfun.conditionals.data.network.NetworkDataSource
import javax.inject.Inject

class ConditionalsInteractor @Inject constructor(
    private val network: NetworkDataSource,
    private val database: DatabaseDataSource
) {

    suspend fun getConditionalsItems(): List<Conditional> {
        val result = network.getConditionalsItems()
        database.save(result)
        return result
    }

    suspend fun searchConditionals(filter: String): List<Conditional> = database.search(filter)
}
