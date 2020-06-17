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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.base.ui.extensions.viewModel
import com.jpaya.base.firebase.FireStoreProperties
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListFragment
import com.jpaya.dynamicfeatures.abbreviations.ui.AbbreviationsListViewModel
import com.jpaya.dynamicfeatures.abbreviations.ui.model.AbbreviationItemMapper
import com.jpaya.dynamicfeatures.abbreviations.ui.paging.AbbreviationsPageDataSourceFactory
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AbbreviationsModuleTest {

    @MockK
    lateinit var fragment: AbbreviationsListFragment
    private lateinit var module: AbbreviationsModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        module = AbbreviationsModule(fragment)
    }

    @Test
    fun initializeCharactersListModule_ShouldSetUpCorrectly() {
        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedAbbreviationsListViewModel() {
        mockkStatic("com.jpaya.base.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<AbbreviationsListViewModel>()

        val factoryCaptor = slot<() -> AbbreviationsListViewModel>()
        val dataFactory = mockk<AbbreviationsPageDataSourceFactory>(relaxed = true)
        module.providesAbbreviationsListViewModel(dataFactory)

        verify { fragment.viewModel(factory = capture(factoryCaptor)) }

        assertEquals(dataFactory, factoryCaptor.captured().dataSourceFactory)
    }

    @Test
    fun verifyProvidedCharactersPageDataSource() {
        val fireStore = mockk<FirebaseFirestore>(relaxed = true)
        val fireStoreProperties = mockk<FireStoreProperties>(relaxed = true)
        val mapper = mockk<AbbreviationItemMapper>(relaxed = true)
        val viewModel = mockk<AbbreviationsListViewModel>(relaxed = true)
        val scope = mockk<CoroutineScope>()
        every { viewModel.viewModelScope } returns scope

        val dataSource = module.providesAbbreviationsPageDataSource(
            fireStore = fireStore,
            fireStoreProperties = fireStoreProperties,
            viewModel = viewModel,
            mapper = mapper
        )

        assertEquals(fireStore, dataSource.fireStore)
        assertEquals(fireStoreProperties, dataSource.fireStoreProperties)
        assertEquals(mapper, dataSource.mapper)
        assertEquals(scope, dataSource.scope)
    }

    @Test
    fun verifyProvidedAbbreviationItemMapper() {
        assertNotNull(module.providesAbbreviationItemMapper())
    }

    @Test
    fun verifyProvidedAbbreviationsListAdapter() {
        val viewModel = mockk<AbbreviationsListViewModel>(relaxed = true)
        val adapter = module.providesAbbreviationsListAdapter(viewModel)
        assertEquals(viewModel, adapter.viewModel)
    }
}
