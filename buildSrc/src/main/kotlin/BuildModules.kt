/**
 * Configuration of build modules
 */
object BuildModules {
    const val BASE_ANDROID = ":base-android"
    const val APP = ":app"
    const val CORE = ":core"

    object Features {
        const val HOME = ":features:home"
        const val ABBREVIATIONS = ":features:abbreviations"
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEWS = ":commons:views"
    }

    object Libraries {
        const val TEST_UTILS = ":libraries:test_utils"
    }
}
