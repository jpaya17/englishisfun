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

package com.jpaya.englishisfun.irregulars.data.db

import android.content.Context
import androidx.room.Room
import com.jpaya.englishisfun.irregulars.domain.Irregulars
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var dao: IrregularsDao

    init {
        val database = Room.databaseBuilder(context, IrregularsDatabase::class.java, "irregulars.db").build()
        dao = database.irregulars()
    }

    fun getSavedIrregulars(): Flow<List<Irregulars>> =
        dao.all().map { news -> news.map(RoomIrregularsItem::toDomain) }

    suspend fun save(item: Irregulars) = dao.save(item.toRoomItem())

    suspend fun delete(id: Long) = dao.delete(id)

    suspend fun delete() = dao.delete()
}
