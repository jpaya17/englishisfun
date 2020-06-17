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

import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor

    fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor
}

/**
 * Base product flavor class that allows to alter the applicationId and the versionName so as
 * being able to create different versions of the same codebase.
 *
 * @param name ApplicationId and versionName suffix
 */
open class ProductFlavorBase(override val name: String) : BuildProductFlavor {

    override fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }

    override fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            versionNameSuffix = "-$name"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
}

/**
 * Develop product flavor implementation using "dev" as suffix.
 */
object ProductFlavorDevelop : ProductFlavorBase("dev")

/**
 * QA product flavor implementation using "qa" as suffix.
 */
object ProductFlavorQA : ProductFlavorBase("qa")

/**
 * Production product flavor implementation.
 */
object ProductFlavorProduction : BuildProductFlavor {
    override val name = "prod"

    override fun app(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        create(container)

    override fun library(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        create(container)

    private fun create(container: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        container.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
}
