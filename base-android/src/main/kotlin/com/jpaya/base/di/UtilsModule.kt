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

import com.jpaya.base.utils.ThemeUtils
import com.jpaya.base.utils.ThemeUtilsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Class that provides dependencies to the hilt dependency graph [ApplicationComponent].
 * All dependencies provided by this class will be considered as application-level properties.
 */
@Module
@InstallIn(ApplicationComponent::class)
class UtilsModule {

    /**
     * Create a provider method binding for [ThemeUtilsImpl].
     *
     * @return Instance of theme utils.
     * @see Provides
     */
    @Provides
    fun providesThemeUtils(): ThemeUtils = ThemeUtilsImpl()
}
