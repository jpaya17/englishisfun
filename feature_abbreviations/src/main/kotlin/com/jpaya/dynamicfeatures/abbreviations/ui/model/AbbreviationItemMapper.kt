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

package com.jpaya.dynamicfeatures.abbreviations.ui.model

import com.jpaya.base.mapper.Mapper
import timber.log.Timber

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class AbbreviationItemMapper : Mapper<MutableList<HashMap<String, String>>, MutableList<AbbreviationItem>> {

    /**
     * Transform network response to [AbbreviationItem].
     *
     * @param from Network abbreviations response.
     * @return List of parsed abbreviations items.
     */
    override suspend fun map(from: MutableList<HashMap<String, String>>): MutableList<AbbreviationItem> {
        val result = mutableListOf<AbbreviationItem>()
        var counter = 1L
        from.map {
            try {
                result.add(
                    AbbreviationItem(
                        id = counter,
                        abbr = it["abbr"]!!,
                        desc = it["desc"]!!
                    )
                )
                counter++
            } catch (ignored: KotlinNullPointerException) {
                Timber.d("AbbreviationItemMapper -> Item ignored")
            }
        }
        return result
    }
}
