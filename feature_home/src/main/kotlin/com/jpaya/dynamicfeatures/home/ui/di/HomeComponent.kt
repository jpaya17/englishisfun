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

package com.jpaya.dynamicfeatures.home.ui.di

import com.jpaya.dynamicfeatures.home.ui.HomeFragment
import com.jpaya.englishisfun.di.dynamicfeatures.HomeModuleDependencies
import dagger.Component

/**
 * Dagger component class to inject dependencies into [HomeFragment] class.
 */
@Component(modules = [HomeModule::class], dependencies = [HomeModuleDependencies::class])
interface HomeComponent {

    /**
     * Inject dependencies on [HomeFragment]
     *
     * @param homeFragment The fragment instance to inject the dependencies into.
     */
    fun inject(homeFragment: HomeFragment)
}
