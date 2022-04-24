enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/dependencies.toml"))
        }
    }
}

rootProject.name = "My Application"
include(":app")
