rootProject.name = "Handgum"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

include(
    ":app",
    ":api-network",

    ":core",
    ":common-entity",

    ":database",

    ":feature:revlist",
    ":feature:revdetails",

    ":navigation",
    ":navigation-api",
)
