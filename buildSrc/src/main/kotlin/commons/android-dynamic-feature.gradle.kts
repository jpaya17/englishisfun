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

package commons

import BuildAndroidConfig
import BuildModules
import BuildProductDimensions
import ProductFlavorDevelop
import ProductFlavorProduction
import ProductFlavorQA
import dependencies.AnnotationProcessorsLibraries
import dependencies.Libraries
import dependencies.TestAndroidLibraries
import dependencies.TestLibraries
import extensions.implementation
import extensions.kapt
import extensions.testImplementation
import extensions.androidTestImplementation

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("androidx.navigation.safeargs.kotlin")
    id("com.vanniktech.android.junit.jacoco")
    id("com.vanniktech.dependency.graph.generator")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    androidExtensions {
        isExperimental = true
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.library(this)
        ProductFlavorQA.library(this)
        ProductFlavorProduction.library(this)
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

junitJacoco {
    includeNoLocationClasses = true
    excludes = excludes.apply {
        if (this == null) {
            listOf("jdk.internal.*")
        } else {
            add("jdk.internal.*")
        }
    }
}

dependencies {
    implementation(project(BuildModules.APP))
    implementation(project(BuildModules.BASE_ANDROID))

    implementation(Libraries.APPCOMPAT)
    implementation(Libraries.CONSTRAINT_LAYOUT)
    implementation(Libraries.CORE_KTX)
    implementation(Libraries.FRAGMENT_KTX)
    implementation(Libraries.HILT)
    implementation(Libraries.HILT_VIEWMODEL)
    implementation(Libraries.KOTLIN)
    implementation(Libraries.KOTLIN_COROUTINES)
    implementation(Libraries.KOTLIN_COROUTINES_ANDROID)
    implementation(Libraries.LIFECYCLE_EXTENSIONS)
    implementation(Libraries.LIFECYCLE_VIEWMODEL)
    implementation(Libraries.NAVIGATION_FRAGMENT)
    implementation(Libraries.NAVIGATION_UI)
    implementation(Libraries.TIMBER)

    kapt(AnnotationProcessorsLibraries.DATABINDING)
    kapt(AnnotationProcessorsLibraries.HILT)
    kapt(AnnotationProcessorsLibraries.ROOM)

    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
    testImplementation(TestLibraries.all())
    androidTestImplementation(TestAndroidLibraries.all())
}
