package dependencies

/**
 * Project annotation processor dependencies, makes it easy to include external binaries or
 * other library modules to build.
 */
object AnnotationProcessorsDependencies {
    const val AUTO_SERVICE = "com.google.auto.service:auto-service:${BuildDependenciesVersions.AUTO_SERVICE}"
    const val DAGGER = "com.google.dagger:dagger-compiler:${BuildDependenciesVersions.DAGGER}"
    const val DATABINDING = "com.android.databinding:compiler:${BuildDependenciesVersions.DATABINDING}"
    const val ROOM = "androidx.room:room-compiler:${BuildDependenciesVersions.ROOM}"

    /**
     * Method to obtain all the annotation processor dependencies.
     *
     * @return An array with all the annotation processor dependencies.
     */
    fun all() = arrayOf(
        AUTO_SERVICE,
        DAGGER,
        DATABINDING,
        ROOM
    )
}
