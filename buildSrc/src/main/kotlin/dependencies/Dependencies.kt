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
 * Project dependencies, makes it easy to include external binaries or other library modules to build.
 */
object Dependencies {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val COIL = "io.coil-kt:coil:${BuildDependenciesVersions.COIL}"
    const val COMPOSE_ANIMATION = "androidx.ui:ui-animation:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_CORE = "androidx.ui:ui-core:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_FOUNDATION = "androidx.ui:ui-foundation:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_LAYOUT = "androidx.ui:ui-layout:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_MATERIAL = "androidx.ui:ui-material:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_MATERIAL_ICONS = "androidx.ui:ui-material-icons-extended:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_RUNTIME = "androidx.compose:compose-runtime:${BuildDependenciesVersions.COMPOSE}"
    const val COMPOSE_TOOLING = "androidx.ui:ui-tooling:${BuildDependenciesVersions.COMPOSE}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildDependenciesVersions.COROUTINES}"
    const val COROUTINES_PLAY_SERVICES = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${BuildDependenciesVersions.COROUTINES}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx:${BuildDependenciesVersions.FIREBASE_ANALYTICS}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth:${BuildDependenciesVersions.FIREBASE_AUTH}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics:${BuildDependenciesVersions.FIREBASE_CRASHLYTICS}"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore-ktx:${BuildDependenciesVersions.FIREBASE_FIRESTORE}"
    const val FIREBASE_PERFORMANCE = "com.google.firebase:firebase-perf:${BuildDependenciesVersions.FIREBASE_PERFORMANCE}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${BuildDependenciesVersions.FRAGMENT}"
    const val HILT = "com.google.dagger:hilt-android:${BuildDependenciesVersions.HILT}"
    const val HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:${BuildDependenciesVersions.HILT_VIEWMODEL}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildDependenciesVersions.KOTLIN}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${BuildDependenciesVersions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${BuildDependenciesVersions.LIFECYCLE}"
    const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildDependenciesVersions.LOGGING}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val MOSHI = "com.squareup.moshi:moshi:${BuildDependenciesVersions.MOSHI}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildDependenciesVersions.MOSHI}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildDependenciesVersions.NAVIGATION}"
    const val PAGING = "androidx.paging:paging-runtime-ktx:${BuildDependenciesVersions.PAGING}"
    const val PLAY_CORE = "com.google.android.play:core:${BuildDependenciesVersions.PLAY_CORE}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${BuildDependenciesVersions.RECYCLER_VIEW}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildDependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${BuildDependenciesVersions.RETROFIT}"
    const val ROOM = "androidx.room:room-runtime:${BuildDependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildDependenciesVersions.ROOM}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${BuildDependenciesVersions.SWIPE_REFRESH_LAYOUT}"
    const val TIMBER = "com.jakewharton.timber:timber:${BuildDependenciesVersions.TIMBER}"
}
