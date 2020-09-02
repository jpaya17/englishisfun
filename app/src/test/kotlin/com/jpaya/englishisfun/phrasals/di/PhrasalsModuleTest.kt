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

package com.jpaya.englishisfun.phrasals.di

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class PhrasalsModuleTest {

    private lateinit var module: PhrasalsModule

    @Before
    fun setUp() {
        module = PhrasalsModule()
    }

    @Test
    fun `Check provided list presenter`() = assertNotNull(module.providesPresenter(mock()))

    @Test
    fun `Check provided interactor`() = assertNotNull(module.providesInteractor(mock(), mock()))

    @Test
    fun `Check provided network data source`() = assertNotNull(module.providesNetworkDataSource(mock()))

    @Test
    fun `Check provided database data source`() = assertNotNull(module.providesDatabaseDataSource(mock()))
}
