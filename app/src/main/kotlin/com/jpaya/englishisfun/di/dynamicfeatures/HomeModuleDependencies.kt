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

package com.jpaya.englishisfun.di.dynamicfeatures

import com.jpaya.base.utils.ThemeUtils
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * This class is needed to connect the hilt dependencies graph to the module 'feature_home'.
 * Note: It is done this way because dynamic features are not supported yet on hilt.
 */
@EntryPoint
@InstallIn(ApplicationComponent::class)
interface HomeModuleDependencies {

    fun bindThemeUtils(): ThemeUtils
}
