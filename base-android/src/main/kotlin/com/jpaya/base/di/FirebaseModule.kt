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

package com.jpaya.base.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.jpaya.base.firebase.FireStoreProperties
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Class that provides Firebase-related dependencies to the hilt dependency graph [ApplicationComponent].
 * All dependencies provided by this class will be considered as application-level properties.
 */
@Module
@InstallIn(ApplicationComponent::class)
class FirebaseModule {

    /**
     * Create a provider method binding for [FirebaseFirestore].
     *
     * @return Instance of FirebaseFirestore.
     * @see Provides
     */
    @Provides
    fun provideFirebaseFireStore() = Firebase.firestore

    /**
     * Create a provider method binding for [FirebaseAuth].
     *
     * @return Instance of FirebaseAuth.
     * @see Provides
     */
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    /**
     * Create a provider method binding for [FireStoreProperties].
     *
     * @return Instance of FireStoreProperties.
     * @see Provides
     */
    @Provides
    fun provideFireStoreProperties() = FireStoreProperties()
}
