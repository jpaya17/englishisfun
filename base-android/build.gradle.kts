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

import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import dependencies.TestDependencies
import dependencies.TestAndroidDependencies
import extensions.androidTestImplementation
import extensions.implementation
import extensions.kapt
import extensions.testImplementation

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.jpaya.base.android.annotations.OpenClass")
}

dependencies {
    implementation(
        arrayOf(
            Dependencies.APPCOMPAT,
            Dependencies.COIL,
            Dependencies.CONSTRAINT_LAYOUT,
            Dependencies.CORE_KTX,
            Dependencies.COROUTINES,
            Dependencies.COROUTINES_ANDROID,
            Dependencies.DAGGER,
            Dependencies.FIREBASE_AUTH,
            Dependencies.FIREBASE_FIRESTORE,
            Dependencies.FRAGMENT_KTX,
            Dependencies.LIFECYCLE_EXTENSIONS,
            Dependencies.LIFECYCLE_VIEWMODEL,
            Dependencies.LOGGING,
            Dependencies.MOSHI,
            Dependencies.MOSHI_KTX,
            Dependencies.NAVIGATION_FRAGMENT,
            Dependencies.NAVIGATION_UI,
            Dependencies.PAGING,
            Dependencies.RECYCLER_VIEW,
            Dependencies.RETROFIT,
            Dependencies.RETROFIT_CONVERTER,
            Dependencies.ROOM,
            Dependencies.ROOM_KTX,
            Dependencies.TIMBER,
            "androidx.activity:activity-ktx:1.2.0-alpha03",
            TestDependencies.CORE,
            TestDependencies.COROUTINES_TEST,
            TestDependencies.JUNIT,
            TestDependencies.MOCKITO
        )
    )
    implementation(TestDependencies.ROBOELECTRIC) {
        exclude(group = "org.apache.maven", module = "maven-ant-tasks")
    }
    kapt(AnnotationProcessorsDependencies.all())
    testImplementation(TestDependencies.all())
}
