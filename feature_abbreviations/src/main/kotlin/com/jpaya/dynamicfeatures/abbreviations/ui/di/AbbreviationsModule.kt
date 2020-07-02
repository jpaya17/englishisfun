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

package com.jpaya.dynamicfeatures.abbreviations.ui.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.base.ui.extensions.viewModel
import com.jpaya.base.firebase.FireStoreProperties
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListFragment
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.adapter.AbbreviationsListAdapter
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSource
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSourceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Class that provides abbreviations-related dependencies to the hilt dependency graph [FragmentComponent].
 * All dependencies provided by this class will be considered as fragment-level properties.
 */
@Module
@InstallIn(FragmentComponent::class)
class AbbreviationsModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: AbbreviationsListFragment
) {

    /**
     * Create a provider method binding for [AbbreviationsListViewModel].
     *
     * @param dataFactory Data source factory for abbreviations.
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    fun providesAbbreviationsListViewModel(
        dataFactory: AbbreviationsPageDataSourceFactory
    ) = fragment.viewModel {
        AbbreviationsListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [AbbreviationsPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesAbbreviationsPageDataSource(
        fireStore: FirebaseFirestore,
        fireStoreProperties: FireStoreProperties,
        viewModel: AbbreviationsListViewModel
    ) = AbbreviationsPageDataSource(
        fireStore = fireStore,
        fireStoreProperties = fireStoreProperties,
        scope = viewModel.viewModelScope
    )

    /**
     * Create a provider method binding for [AbbreviationsListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @Provides
    fun providesAbbreviationsListAdapter(
        viewModel: AbbreviationsListViewModel
    ) = AbbreviationsListAdapter(viewModel)
}
