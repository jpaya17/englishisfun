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

package com.jpaya.englishisfun.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.englishisfun.firestore.FireStoreClientImpl
import com.jpaya.englishisfun.firestore.FireStoreProperties
import io.mockk.mockk
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class FirebaseModuleTest {

    private lateinit var module: FirebaseModule

    @Before
    fun setUp() {
        module = FirebaseModule()
    }

    @Test
    fun verifyProvidedFireStoreProperties() {
        assertNotNull(module.provideFireStoreProperties())
    }

    @Test
    fun verifyProvidedFireStoreClient() {
        val fireStore = mockk<FirebaseFirestore>(relaxed = true)
        val fireStoreProperties = mockk<FireStoreProperties>(relaxed = true)

        val fireStoreClient = module.providesFireStoreClient(fireStore, fireStoreProperties)

        assertThat(fireStoreClient, instanceOf(FireStoreClientImpl::class.java))
        assertEquals(fireStore, (fireStoreClient as FireStoreClientImpl).fireStore)
        assertEquals(fireStoreProperties, fireStoreClient.properties)
    }
}
