pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SearchBook"
include(":app")
include(":core:data")
include(":core:network")
include(":core:designsystem")

include(":feature:search")
include(":feature:detail")
include(":core:domain")
include(":core:ui")
include(":core:testing")
