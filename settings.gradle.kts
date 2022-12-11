@file:Suppress("UnstableApiUsage")

rootProject.name = "AdminCommands"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("minestom", "com.github.Minestom:Minestom:-SNAPSHOT")
            library("citystom", "me.heroostech.citystom:CityStom:v1.0.0")
            library("lombok", "org.projectlombok:lombok:1.18.24")
            library("permissions", "com.github.MinestomPlugins:lib-permissions:5a9310e71f")
        }
    }
}