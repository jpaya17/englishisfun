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

package com.jpaya.base.ui.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import com.jpaya.base.ui.extensions.observe
import com.jpaya.libraries.testutils.lifecycle.TestLifecycleOwner
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class SingleLiveDataTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun setUp() {
        lifecycleOwner = TestLifecycleOwner()
    }

    @Test
    fun observingSingleLiveData_WhenPostStringValue_ShouldTriggerOneEvent() {
        val singleLiveData = com.jpaya.base.ui.livedata.SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer = mock<(String) -> Unit>()
        val observerCaptor = argumentCaptor<String>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)

        verify(observer).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun observingSingleLiveData_WhenPostMultipleIntValue_ShouldTriggerMultipleTimes() {
        val singleLiveData = com.jpaya.base.ui.livedata.SingleLiveData<Int>()
        val observerPostValue = 1
        val observer = mock<(Int) -> Unit>()
        val observerCaptor = argumentCaptor<Int>()

        lifecycleOwner.observe(singleLiveData, observer)
        singleLiveData.postValue(observerPostValue)
        singleLiveData.postValue(observerPostValue)

        verify(observer, times(2)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)

        singleLiveData.postValue(observerPostValue)

        verify(observer, times(3)).invoke(observerCaptor.capture())
        assertEquals(observerPostValue, observerCaptor.lastValue)
    }

    @Test
    fun multipleObservingSingleLiveData_WhenPostIntValue_ShouldTriggerOnlyFirstObserver() {
        val singleLiveData = com.jpaya.base.ui.livedata.SingleLiveData<String>()
        val observerPostValue = "Event Value"
        val observer1 = mock<(String) -> Unit>()
        val observer2 = mock<(String) -> Unit>()
        val observer3 = mock<(String) -> Unit>()
        val observer1Captor = argumentCaptor<String>()

        lifecycleOwner.observe(singleLiveData, observer1)
        lifecycleOwner.observe(singleLiveData, observer2)
        lifecycleOwner.observe(singleLiveData, observer3)
        singleLiveData.postValue(observerPostValue)

        verify(observer1).invoke(observer1Captor.capture())
        verify(observer2, never()).invoke(anyString())
        verify(observer3, never()).invoke(anyString())

        assertEquals(observerPostValue, observer1Captor.lastValue)
    }

    @Test
    fun observingSingleLiveData_WithoutPostValue_ShouldNotTrigger() {
        val singleLiveData = com.jpaya.base.ui.livedata.SingleLiveData<Int>()
        val observer = mock<(Int) -> Unit>()

        lifecycleOwner.observe(singleLiveData, observer)

        verify(observer, never()).invoke(anyInt())
    }
}
