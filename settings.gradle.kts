pluginManagement.repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://maven.architectury.dev/")
    maven("https://maven.minecraftforge.net/")
    gradlePluginPortal()
}

plugins {
    id("com.gradle.enterprise") version "3.13.4"
}

gradleEnterprise.buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

include("common", "fabric", "forge")

rootProject.name = "generations-core"