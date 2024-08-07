pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "JetNews"

include(":app")

include(":core:network")
include(":core:design")
include(":core:common")
include(":core:navigation")
include(":core:dataStore")
include(":core:domain")
include(":core:data")
include(":core:model")

include(":feature:home")
include(":feature:details")
