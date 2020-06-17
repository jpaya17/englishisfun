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

import dependencies.*
import extensions.*

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.FIREBASE_CRASHLYTICS)
//    id(BuildPlugins.FIREBASE_PERFORMANCE)
    id(BuildPlugins.GOOGLE_SERVICES)
    id(BuildPlugins.GRAPH_GENERATOR)
    id(BuildPlugins.HILT)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.jpaya.core.annotations.OpenClass")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS
    }

    signingConfigs {
        create(BuildType.RELEASE) {
            keyAlias = getLocalProperty("signing.key.alias")
            keyPassword = getLocalProperty("signing.key.password")
            storeFile = file(getLocalProperty("signing.store.file"))
            storePassword = getLocalProperty("signing.store.password")
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName(name)

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)
    productFlavors {
        ProductFlavorDevelop.app(this)
        ProductFlavorQA.app(this)
        ProductFlavorProduction.app(this)
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.HOME,
        BuildModules.Features.ABBREVIATIONS
    )

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "0.1.0-dev12"
        kotlinCompilerVersion = "1.3.70-dev-withExperimentalGoogleExtensions-20200424"
    }

    androidExtensions {
        isExperimental = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
}

junitJacoco {
    includeNoLocationClasses = true
}

afterEvaluate {
}

dependencies {
    implementation(project(BuildModules.BASE_ANDROID))
    implementation(
        arrayOf(
            Dependencies.APPCOMPAT,
            Dependencies.COMPOSE_MATERIAL,
            Dependencies.COMPOSE_RUNTIME,
            Dependencies.COMPOSE_TOOLING,
            Dependencies.CONSTRAINT_LAYOUT,
            Dependencies.FIREBASE_ANALYTICS,
            Dependencies.FIREBASE_AUTH,
            Dependencies.FIREBASE_CRASHLYTICS,
            Dependencies.FIREBASE_FIRESTORE,
            Dependencies.FIREBASE_PERFORMANCE,
            Dependencies.HILT,
            Dependencies.HILT_VIEWMODEL,
            Dependencies.KOTLIN,
            Dependencies.LOGGING,
            Dependencies.MATERIAL,
            Dependencies.NAVIGATION_FRAGMENT,
            Dependencies.PLAY_CORE,
            Dependencies.TIMBER
        )
    )
    debugImplementation(DebugDependencies.LEAKCANARY)
    kapt(
        arrayOf(
            AnnotationProcessorsDependencies.HILT,
            AnnotationProcessorsDependencies.HILT_VIEWMODEL
        )
    )
    testImplementation(TestDependencies.all())
    androidTestImplementation(TestAndroidDependencies.all())
}
