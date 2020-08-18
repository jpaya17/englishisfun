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

package com.jpaya.englishisfun.conditionals.data.db

import com.jpaya.base.extensions.encloseToLikeQuery
import com.jpaya.englishisfun.conditionals.domain.Conditionals
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseDataSource @Inject constructor(
    private val dao: ConditionalsDao
) {

    suspend fun all(): List<Conditionals> = dao.all().map { item -> item.toDomain() }

    suspend fun count() = dao.count()

    suspend fun save(item: Conditionals) = dao.save(item.toRoomItem())

    suspend fun save(items: List<Conditionals>) {
        val objects = mutableListOf<RoomConditionalsItem>()
        items.forEach {
            objects.add(it.toRoomItem())
        }
        dao.save(objects)
    }

    suspend fun search(filter: String): List<Conditionals> {
        return dao.search(filter.encloseToLikeQuery()).map {
            Conditionals(
                id = it.id,
                base = it.base,
                simple = it.simple,
                participle = it.participle,
                definitions = it.definitions
            )
        }
    }

    suspend fun delete(id: Long) = dao.delete(id)

    suspend fun delete() = dao.delete()
}
