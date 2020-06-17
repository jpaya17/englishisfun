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

package com.jpaya.englishisfun.di

import com.jpaya.englishisfun.EnglishIsFunApp
import com.jpaya.englishisfun.di.modules.AppModule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AppModuleTest {

    private lateinit var appModule: AppModule

    @Before
    fun setUp() {
        appModule = AppModule()
    }

    @Test
    fun verifyProvidedContext() {
        val application: EnglishIsFunApp = mock()
        doReturn(application).whenever(application).applicationContext

        assertNotNull(appModule.provideContext(application))
        assertEquals(application, appModule.provideContext(application))
    }
}
