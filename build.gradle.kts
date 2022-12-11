plugins {
    id("java")
    id ("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "me.heroostech.admincommands"
version = "v1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly(libs.minestom)
    implementation(libs.citystom)
    compileOnly(libs.lombok)
    compileOnly("dev.emortal.tnt:TNT:unspecified")
    annotationProcessor(libs.lombok)
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("AdminCommands.jar")
}