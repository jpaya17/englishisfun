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

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.jpaya.base.network.NetworkState
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSourceFactory
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [AbbreviationsListFragment].
 *
 * @see ViewModel
 */
class AbbreviationsListViewModel
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: AbbreviationsPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    AbbreviationsListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    AbbreviationsListViewState.Empty
                } else {
                    AbbreviationsListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    AbbreviationsListViewState.AddLoading
                } else {
                    AbbreviationsListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    AbbreviationsListViewState.AddError
                } else {
                    AbbreviationsListViewState.Error
                }
        }
    }

    /**
     * Refresh abbreviations fetch them again and update the list.
     */
//    fun refreshLoadedAbbreviationsList() {
//        dataSourceFactory.refresh()
//    }
}
