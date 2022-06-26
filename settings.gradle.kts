enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/dependencies.toml"))
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "Knowlly"

includeAll(
    "app",
    "presentation",
    "data",
    "domain",
    "remote",
    "local",
    "log",
    "core-exception"
)

fun includeAll(vararg names: String) {
    names.forEach { name ->
        val projectName = ":$name"
        val projectPath = "modules/$name"
        include(projectName)
        project(projectName).projectDir = file(projectPath)
    }
}
