@file:Suppress("UnstableApiUsage")

rootProject.name = "AdminCommands"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("minestom", "com.github.minestom.minestom:Minestom:-SNAPSHOT")
            library("lombok", "org.projectlombok:lombok:1.18.24")
            library("nextlib", "com.github.MinestomPlugins:NextLib:master-SNAPSHOT")
        }
    }
}