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

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
    maven("https://ci.android.com/builds/submitted/5837096/androidx_snapshot/latest/repository")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object GradleVersions {
    const val DETEKT = "1.15.0"
    const val FIREBASE_CRASHLYTICS = "2.4.1"
    const val FIREBASE_PERFORMANCE = "1.3.4"
    const val GOOGLE_SERVICES = "4.3.4"
    const val GRADLE_ANDROID = "4.1.2"
    const val GRADLE_VERSIONS = "0.36.0"
    const val HILT = "2.28-alpha"
    const val JACOCO = "0.16.0"
    const val KOTLIN = "1.4.21"
    const val KTLINT = "0.40.0"
    const val NAVIGATION = "2.3.2"
    const val SONARQUBE = "3.0"
    const val SPOTLESS = "5.9.0"
}

object GradlePlugins {
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${GradleVersions.DETEKT}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-gradle:${GradleVersions.FIREBASE_CRASHLYTICS}"
    const val FIREBASE_PERFORMANCE = "com.google.firebase:perf-plugin:${GradleVersions.FIREBASE_PERFORMANCE}"
    const val GOOGLE_SERVICES = "com.google.gms:google-services:${GradleVersions.GOOGLE_SERVICES}"
    const val GRADLE_ANDROID = "com.android.tools.build:gradle:${GradleVersions.GRADLE_ANDROID}"
    const val GRADLE_VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${GradleVersions.GRADLE_VERSIONS}"
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${GradleVersions.HILT}"
    const val JACOCO = "com.vanniktech:gradle-android-junit-jacoco-plugin:${GradleVersions.JACOCO}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradleVersions.KOTLIN}"
    const val KOTLIN_ALLOPEN = "org.jetbrains.kotlin:kotlin-allopen:${GradleVersions.KOTLIN}"
    const val KTLINT = "com.pinterest:ktlint:${GradleVersions.KTLINT}"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${GradleVersions.NAVIGATION}"
    const val SONARQUBE = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${GradleVersions.SONARQUBE}"
    const val SPOTLESS = "com.diffplug.spotless:spotless-plugin-gradle:${GradleVersions.SPOTLESS}"
}

dependencies {
    implementation(GradlePlugins.DETEKT)
    implementation(GradlePlugins.FIREBASE_CRASHLYTICS)
    implementation(GradlePlugins.FIREBASE_PERFORMANCE)
    implementation(GradlePlugins.GOOGLE_SERVICES)
    implementation(GradlePlugins.GRADLE_ANDROID)
    implementation(GradlePlugins.GRADLE_VERSIONS)
    implementation(GradlePlugins.HILT)
    implementation(GradlePlugins.JACOCO)
    implementation(GradlePlugins.KOTLIN)
    implementation(GradlePlugins.KOTLIN_ALLOPEN)
    implementation(GradlePlugins.KTLINT)
    implementation(GradlePlugins.NAVIGATION_SAFE_ARGS)
    implementation(GradlePlugins.SONARQUBE)
    implementation(GradlePlugins.SPOTLESS)
}
