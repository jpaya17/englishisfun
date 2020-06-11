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

package com.jpaya.base.android.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.base.android.di.modules.ContextModule
import com.jpaya.base.android.di.modules.FirebaseModule
import com.jpaya.base.android.di.modules.UtilsModule
import com.jpaya.base.android.firebase.FireStoreProperties
import com.jpaya.base.android.utils.ThemeUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ContextModule::class,
    UtilsModule::class,
    FirebaseModule::class
])
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils

    /**
     * Provide dependency graph FirebaseFirestore
     *
     * @return FirebaseFirestore
     */
    fun firebaseFirestore(): FirebaseFirestore

    /**
     * Provide dependency graph FirebaseAuth
     *
     * @return FirebaseAuth
     */
    fun firebaseAuth(): FirebaseAuth

    /**
     * Provide dependency graph FireStoreProperties
     *
     * @return FireStoreProperties
     */
    fun fireStoreProperties(): FireStoreProperties
}
