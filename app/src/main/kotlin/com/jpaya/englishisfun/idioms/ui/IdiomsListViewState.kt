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

sealed class IdiomsListViewState {
    abstract fun showLoading(): Boolean
    abstract fun showError(): Boolean
    abstract fun showList(): Boolean
    abstract fun showEmpty(): Boolean
    abstract fun list(): List<SimpleListItem2>

    object Loading : IdiomsListViewState() {
        override fun showLoading(): Boolean = true
        override fun showError(): Boolean = false
        override fun showList(): Boolean = false
        override fun showEmpty(): Boolean = false
        override fun list(): List<SimpleListItem2> = listOf()
    }

    data class ListReady(private val idioms: List<SimpleListItem2>) : IdiomsListViewState() {
        override fun showLoading(): Boolean = false
        override fun showError(): Boolean = false
        override fun showList(): Boolean = idioms.isEmpty().not()
        override fun showEmpty(): Boolean = idioms.isEmpty()
        override fun list(): List<SimpleListItem2> = idioms
    }

    object NetworkError : IdiomsListViewState() {
        override fun showLoading(): Boolean = false
        override fun showError(): Boolean = true
        override fun showList(): Boolean = false
        override fun showEmpty(): Boolean = false
        override fun list(): List<SimpleListItem2> = listOf()
    }
}
