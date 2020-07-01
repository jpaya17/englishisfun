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

package com.jpaya.dynamicfeatures.abbreviations.ui.paging

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Provider

@RunWith(MockitoJUnitRunner::class)
class AbbreviationsPageDataSourceFactoryTest {

    @Spy
    lateinit var providerDataSource: Provider<AbbreviationsPageDataSource>

    @Spy
    lateinit var sourceLiveData: MutableLiveData<AbbreviationsPageDataSource>

    @InjectMocks
    lateinit var dataSourceFactory: AbbreviationsPageDataSourceFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun init_WithoutCreate_ShouldNotHaveDataSource() {
        verify(dataSourceFactory.sourceLiveData, never())
        assertNull(dataSourceFactory.sourceLiveData.value)
    }

    @Test
    fun init_WithCreate_ShouldHaveDataSource() {
        doReturn(AbbreviationsPageDataSource(mock(), mock())).whenever(providerDataSource).get()
        val dataSource = dataSourceFactory.create() as AbbreviationsPageDataSource
        verify(dataSourceFactory.sourceLiveData).postValue(same(dataSource))
    }

    @Test
    fun refreshDataSource_WithValue_ShouldInvalidateData() {
        val dataSource = mock<AbbreviationsPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.refresh()

        verify(dataSource).invalidate()
        verify(dataSource, never()).retry()
    }

    @Test
    fun refreshDataSource_WithoutValue_ShouldInvalidateData() {
        val dataSource = mock<AbbreviationsPageDataSource>()
        doReturn(null).whenever(sourceLiveData).value

        dataSourceFactory.refresh()

        verify(dataSource, never()).invalidate()
        verify(dataSource, never()).retry()
    }

    @Test
    fun retryDataSource_WithValue_ShouldRetryData() {
        val dataSource = mock<AbbreviationsPageDataSource>()
        doReturn(dataSource).whenever(sourceLiveData).value

        dataSourceFactory.retry()

        verify(dataSource).retry()
        verify(dataSource, never()).invalidate()
    }

    @Test
    fun retryDataSource_WithoutValue_ShouldRetryData() {
        val dataSource = mock<AbbreviationsPageDataSource>()
        doReturn(null).whenever(sourceLiveData).value

        dataSourceFactory.retry()

        verify(dataSource, never()).retry()
        verify(dataSource, never()).invalidate()
    }
}
