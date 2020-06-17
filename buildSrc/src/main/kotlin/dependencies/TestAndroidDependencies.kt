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

package dependencies

/**
 * Project Android test dependencies, makes it easy to include external binaries or other library modules to build.
 */
object TestAndroidDependencies {
    const val COMPOSE_CORE = "androidx.ui:ui-core:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_TEST = "androidx.ui:ui-test:${BuildDependenciesVersions.COMPOSE}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${BuildDependenciesVersions.ESPRESSO}"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing:${BuildDependenciesVersions.FRAGMENT}"
    const val JUNIT = "androidx.test.ext:junit:${BuildDependenciesVersions.EXT}"
    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android-instrumentation:${BuildDependenciesVersions.LEAKCANARY}"
    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:${BuildDependenciesVersions.MOCKITO}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val RULES = "androidx.test:rules:${BuildDependenciesVersions.TEST}"
    const val RUNNER = "androidx.test:runner:${BuildDependenciesVersions.TEST}"

    /**
     * Method to obtain all the Android test dependencies.
     *
     * @return An array with all the Android test dependencies.
     */
    fun all() = arrayOf(
        COMPOSE_CORE,
        COMPOSE_TEST,
        ESPRESSO,
        FRAGMENT_TEST,
        JUNIT,
        LEAKCANARY,
        MOCKITO,
        PLAY_CORE,
        RULES,
        RUNNER
    )
}
