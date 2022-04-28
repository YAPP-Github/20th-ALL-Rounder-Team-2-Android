enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/dependencies.toml"))
        }
    }
}

rootProject.name = "My Application"

includeAll(
    "app",
    "presentation",
    "data",
    "domain",
    "remote",
    "local"
)

fun includeAll(vararg names: String) {
    names.forEach { name ->
        val projectName = ":$name"
        val projectPath = "modules/$name"
        include(projectName)
        project(projectName).projectDir = file(projectPath)
    }
}
