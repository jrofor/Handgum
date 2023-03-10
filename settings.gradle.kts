rootProject.name = "Handgum"

enableFeaturePreview("VERSION_CATALOGS")

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
