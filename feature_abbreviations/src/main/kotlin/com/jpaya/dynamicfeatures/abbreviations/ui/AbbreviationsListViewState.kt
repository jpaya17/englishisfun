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

package com.jpaya.dynamicfeatures.abbreviations.ui

import com.jpaya.base.ui.base.BaseViewState

/**
 * Different states for [AbbreviationsListFragment].
 *
 * @see BaseViewState
 */
sealed class AbbreviationsListViewState : BaseViewState {

    /**
     * Refreshing abbreviations list.
     */
    object Refreshing : AbbreviationsListViewState()

    /**
     * Loaded abbreviations list.
     */
    object Loaded : AbbreviationsListViewState()

    /**
     * Loading abbreviations list.
     */
    object Loading : AbbreviationsListViewState()

    /**
     * Loading on add more elements into abbreviations list.
     */
    object AddLoading : AbbreviationsListViewState()

    /**
     * Empty abbreviations list.
     */
    object Empty : AbbreviationsListViewState()

    /**
     * Error on loading abbreviations list.
     */
    object Error : AbbreviationsListViewState()

    /**
     * Error on add more elements into abbreviations list.
     */
    object AddError : AbbreviationsListViewState()

    /**
     * No more elements for adding into abbreviations list.
     */
    object NoMoreElements : AbbreviationsListViewState()

    /**
     * Check if current view state is [Refreshing].
     *
     * @return True if is refreshing state, otherwise false.
     */
    fun isRefreshing() = this is Refreshing

    /**
     * Check if current view state is [Loaded].
     *
     * @return True if is loaded state, otherwise false.
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [AddLoading].
     *
     * @return True if is add loading state, otherwise false.
     */
    fun isAddLoading() = this is AddLoading

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [AddError].
     *
     * @return True if is add error state, otherwise false.
     */
    fun isAddError() = this is AddError

    /**
     * Check if current view state is [NoMoreElements].
     *
     * @return True if is no more elements state, otherwise false.
     */
    fun isNoMoreElements() = this is NoMoreElements
}
